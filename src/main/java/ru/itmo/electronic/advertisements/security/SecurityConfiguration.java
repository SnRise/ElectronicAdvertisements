package ru.itmo.electronic.advertisements.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itmo.electronic.advertisements.service.UserService;

/**
 * @author Madiyar Nurgazin
 */
@Configuration
public class SecurityConfiguration {
    @Bean
    public Algorithm jwtAlgorithm(
            @Value("${jwt.secret}") String jwtSecret
    ) {
        return Algorithm.HMAC256(jwtSecret);
    }

    @Bean
    public JWTVerifier verifier(Algorithm algorithm, @Value("${token.issuer}") String tokenIssuer) {
        return JWT.require(algorithm)
                .withIssuer(tokenIssuer)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(@Value("${password.strength}") int passwordStrength) {
        return new BCryptPasswordEncoder(passwordStrength);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
