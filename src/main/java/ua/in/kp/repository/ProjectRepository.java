package ua.in.kp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.kp.entity.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, String> {

}
