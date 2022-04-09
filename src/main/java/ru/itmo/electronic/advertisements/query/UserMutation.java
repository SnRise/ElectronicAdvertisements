package ru.itmo.electronic.advertisements.query;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.itmo.electronic.advertisements.model.User;
import ru.itmo.electronic.advertisements.service.UserService;

/**
 * @author Madiyar Nurgazin
 */
@Component
@RequiredArgsConstructor
public class UserMutation implements GraphQLMutationResolver {
    private final UserService userService;
    private final AuthenticationProvider authenticationProvider;

    public User createUser(String email, String password, String name) {
        return userService.createUser(email, password, name);
    }

    @PreAuthorize("isAnonymous()")
    public User login(String email, String password) {
        UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(email, password);
        try {
            SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(credentials));
            return userService.getCurrentUser();
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException(email);
        }
    }

    @PreAuthorize("isAuthenticated()")
    public boolean logout() {
        SecurityContextHolder.clearContext();
        return true;
    }

    @PreAuthorize("isAuthenticated()")
    public String getToken(User user) {
        return userService.getToken(user);
    }
}
