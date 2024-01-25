package ua.in.kp.repository;

public interface TagRepository {
    void saveByNameIfNotExist(String tagName);
}
