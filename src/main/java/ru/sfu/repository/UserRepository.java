package ru.sfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfu.entity.User;

/**
 * JPA Repository Interface for User entities
 * @author Agapchenko V.V.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
