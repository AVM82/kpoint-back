package ua.in.kp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.in.kp.config.MapperConfig;
import ua.in.kp.dto.user.UserResponseDto;
import ua.in.kp.entity.ApplicantEntity;

@Mapper(config = MapperConfig.class)
public interface ApplicantMapper {

    @Mapping(target = "roles", expression = "java(Set.of(UserRole.GUEST))")
    UserResponseDto toUserResponseDto(ApplicantEntity entity);
}
