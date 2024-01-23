package ua.in.kp.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import ua.in.kp.config.MapperConfig;
import ua.in.kp.dto.user.UserCreateRequestDto;
import ua.in.kp.dto.user.UserResponseDto;
import ua.in.kp.entity.UserEntity;

@Mapper(config = MapperConfig.class, uses = Collectors.class)
public interface UserMapper {

    UserEntity toEntity(UserCreateRequestDto dto);

    UserResponseDto toDto(UserEntity user);

    default Set<String> tagsToLowerCase(Set<String> tags) {
        return tags.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }
}
