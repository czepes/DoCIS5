package ru.sfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sfu.entity.Television;

import java.util.List;

/**
 * JPA Repository Interface for Television entities
 * @author Agapchenko V.V.
 */
@Repository
public interface TelevisionRepository extends JpaRepository<Television, Integer> {
    List<Television> findByWidthAndHeight(
            Integer width,
            Integer height
    );
}
