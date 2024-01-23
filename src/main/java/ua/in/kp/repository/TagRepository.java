package ua.in.kp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.in.kp.entity.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, String> {
}
