package ua.in.kp.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.kp.entity.ApplicantEntity;

public interface ApplicantRepository extends JpaRepository<ApplicantEntity, UUID> {
    Optional<ApplicantEntity> findByEmail(String email);
}
