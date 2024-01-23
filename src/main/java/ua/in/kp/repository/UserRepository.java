package ua.in.kp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.kp.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
