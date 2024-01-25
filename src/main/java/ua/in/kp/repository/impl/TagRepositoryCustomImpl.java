package ua.in.kp.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.in.kp.repository.TagRepositoryCustom;

@Component
@RequiredArgsConstructor
public class TagRepositoryCustomImpl implements TagRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveByNameIfNotExist(String tagName) {
        String query = "INSERT INTO tags_index (name) VALUES (?) ON CONFLICT DO NOTHING";
        entityManager.createNativeQuery(query)
                .setParameter(1, tagName)
                .executeUpdate();
    }
}
