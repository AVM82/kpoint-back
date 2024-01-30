package ua.in.kp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ua.in.kp.config.MapperConfig;
import ua.in.kp.dto.project.GetAllProjectsDto;
import ua.in.kp.dto.project.ProjectCreateRequestDto;
import ua.in.kp.dto.project.ProjectResponseDto;
import ua.in.kp.entity.ProjectEntity;

@Mapper(config = MapperConfig.class, uses = TagMapper.class)
public interface ProjectMapper {

    @Mapping(target = "collectDeadline", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "goalDeadline", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "owner.ownerId", source = "owner.id")
    ProjectResponseDto toDto(ProjectEntity projectEntity);

    ProjectEntity toEntity(ProjectCreateRequestDto projectDto);

    void updateProjectFromDto(ProjectCreateRequestDto dto, @MappingTarget ProjectEntity entity);

    GetAllProjectsDto getAllToDto(ProjectEntity projectEntity);
}
