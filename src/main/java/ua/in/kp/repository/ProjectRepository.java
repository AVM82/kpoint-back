package ua.in.kp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.in.kp.entity.ProjectEntity;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, String> {
    @Override
    @Query("FROM ProjectEntity p left join fetch p.tags "
            + "left join fetch p.networksLinks where p.projectId=:id")
    Optional<ProjectEntity> findById(String id);
}
