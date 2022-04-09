package ru.itmo.electronic.advertisements.query;

import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import ru.itmo.electronic.advertisements.model.User;
import ru.itmo.electronic.advertisements.service.UserService;

/**
 * @author Madiyar Nurgazin
 */
@Component
@RequiredArgsConstructor
public class UserResolver implements GraphQLResolver<User> {
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    public String getToken(User user) {
        return userService.getToken(user);
    }
}
