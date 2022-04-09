package ru.itmo.electronic.advertisements.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.electronic.advertisements.jwt.JWTUserDetails;
import ru.itmo.electronic.advertisements.model.User;
import ru.itmo.electronic.advertisements.repository.UserRepository;

/**
 * @author Madiyar Nurgazin
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final PasswordEncoder passwordEncoder;

    @Value("${token.expiration}")
    private int tokenExpiration;
    @Value("${token.issuer}")
    private String tokenIssuer;

    @Override
    public JWTUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository
                .findByEmail(email)
                .map(user -> getUserDetails(user, getToken(user)))
                .orElseThrow(() -> new UsernameNotFoundException("Username or password didn't match"));
    }

    @Transactional
    public JWTUserDetails loadUserByToken(String token) {
        return getDecodedToken(token)
                .map(DecodedJWT::getSubject)
                .flatMap(repository::findByEmail)
                .map(user -> getUserDetails(user, token))
                .orElseThrow(() -> new IllegalArgumentException("Illegal token: " + token));
    }

    @Transactional
    public User createUser(String email, String password, String name) {
        if (!exists(email)) {
            return repository.save(User.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .name(name)
                    .role("USER")
                    .build()
            );
        } else {
            throw new IllegalArgumentException("User with email '" + email + "' already exists");
        }
    }

    @Transactional
    public User getCurrentUser() {
        return Optional
                .ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName)
                .flatMap(repository::findByEmail)
                .orElse(null);
    }

    @Transactional
    public String getToken(User user) {
        Instant now = Instant.now();
        Instant expiry = Instant.now().plus(Duration.ofDays(tokenExpiration));
        return JWT
                .create()
                .withIssuer(tokenIssuer)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(expiry))
                .withSubject(user.getEmail())
                .sign(algorithm);
    }

    private JWTUserDetails getUserDetails(User user, String token) {
        return JWTUserDetails
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .token(token)
                .role(user.getRole())
                .build();
    }

    private Optional<DecodedJWT> getDecodedToken(String token) {
        try {
            return Optional.of(verifier.verify(token));
        } catch(JWTVerificationException ex) {
            return Optional.empty();
        }
    }

    private boolean exists(String email) {
        return repository.existsByEmail(email);
    }
}
