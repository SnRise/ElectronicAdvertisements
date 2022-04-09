package ru.itmo.electronic.advertisements.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.electronic.advertisements.model.User;

/**
 * @author Madiyar Nurgazin
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
