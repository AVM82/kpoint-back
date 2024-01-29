package ua.in.kp.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.in.kp.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("FROM UserEntity u LEFT JOIN FETCH u.roles "
            + "LEFT JOIN FETCH u.socialNetworks WHERE u.email=:principal OR u.username=:principal")
    Optional<UserEntity> findByEmailOrUsername(String principal);

    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = {"socialNetworks", "roles"})
    Page<UserEntity> findAll(Pageable pageable);
}
