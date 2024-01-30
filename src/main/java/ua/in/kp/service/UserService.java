package ua.in.kp.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.in.kp.dto.user.UserRegisterRequestDto;
import ua.in.kp.dto.user.UserResponseDto;
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
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService customUserDetailsService;

    @Transactional
    public UserResponseDto create(UserRegisterRequestDto dto) {
        UserEntity mappedEntity = userMapper.toEntity(dto);
        mappedEntity.setPassword(passwordEncoder.encode(mappedEntity.getPassword()));
        mappedEntity.getTags().forEach(t -> tagRepository.saveByNameIfNotExist(t.getName()));
        return userMapper.toDto(userRepository.save(mappedEntity));
    }

    public List<UserResponseDto> getAll(Pageable pageable) {
        return userRepository.findAll(pageable).stream().map(userMapper::toDto).toList();
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserEntity getAuthenticated() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return (UserEntity) customUserDetailsService.loadUserByUsername(email);
    }
}
