package ua.in.kp.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.in.kp.dto.user.UserCreateRequestDto;
import ua.in.kp.dto.user.UserResponseDto;
import ua.in.kp.entity.TagEntity;
import ua.in.kp.entity.UserEntity;
import ua.in.kp.mapper.UserMapper;
import ua.in.kp.repository.TagRepository;
import ua.in.kp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final TagRepository tagRepository;

    private final UserMapper userMapper;

    @Transactional
    public UserResponseDto create(UserCreateRequestDto dto) {
        UserEntity mappedEntity = userMapper.toEntity(dto);
        //        Set<TagEntity> nonPersistentTags = getNonPersistentTags(mappedEntity);
        mappedEntity.getTags().stream()
                .filter(n -> !tagRepository.existsById(n))
                .forEach(n -> tagRepository.save(TagEntity.builder().name(n).build()));

        //        mappedEntity.getTags().forEach(tagRepository::save);

        //        tagRepository.saveAll(nonPersistentTags);
        //        nonPersistentTags.forEach(tagRepository::save);
        return userMapper.toDto(userRepository.save(mappedEntity));
    }

    public List<UserResponseDto> getAll(Pageable pageable) {
        return userRepository.findAll(pageable).stream().map(userMapper::toDto).toList();
    }

//    private Set<TagEntity> getNonPersistentTags(UserEntity entity) {
//        Set<String> persistentTags = tagRepository.findAllById(entity.getTags()).stream()
//                .map(TagEntity::getName)
//                .collect(Collectors.toSet());
//        return Sets.difference(entity.getTags(), persistentTags).stream()
//                .map(n -> TagEntity.builder().name(n).build())
//                .collect(Collectors.toSet());
//    }
}
