package ru.itmo.electronic.advertisements.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.electronic.advertisements.model.User;
import ru.itmo.electronic.advertisements.service.UserService;

/**
 * @author Madiyar Nurgazin
 */

@Component
@RequiredArgsConstructor
public class UserQuery implements GraphQLQueryResolver {
    private final UserService userService;

    public User getCurrentUser() {
        return userService.getCurrentUser();
    }
}
