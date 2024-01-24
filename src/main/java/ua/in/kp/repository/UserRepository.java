package ua.in.kp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.in.kp.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("FROM UserEntity u LEFT JOIN FETCH u.roles "
            + "LEFT JOIN FETCH u.socialNetworks WHERE u.email=:email")
    Optional<UserEntity> findByEmail(String email);

    boolean existsAllByEmail(String email);
}
