//package ua.in.kp.repository.impl;
//
//import jakarta.persistence.EntityManager;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import ua.in.kp.repository.TagRepository;
//
//@Component
//@RequiredArgsConstructor
//public class TagRepositoryImpl implements TagRepository {
//
//    private final EntityManager entityManager;
//
//    @Override
//    public void save(String tagName) {
//        String query = "INSERT INTO tags_index (name) VALUES (?) ON CONFLICT DO NOTHING";
//        entityManager.createNativeQuery(query)
//                .setParameter(1, tagName)
//                .executeUpdate();
//    }
//}
