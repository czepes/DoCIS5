package ru.sfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfu.entity.Role;

/**
 * JPA Repository Interface for Role entities
 * @author Agapchenko V.V.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
