package ua.in.kp.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.kp.entity.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, String> {

    @EntityGraph(attributePaths = {"tags", "networksLinks"})
    Page<ProjectEntity> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"tags", "networksLinks"})
    Optional<ProjectEntity> findById(String projectId);
}
