package ua.in.kp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.in.kp.config.MapperConfig;
import ua.in.kp.dto.project.ProjectCreateRequestDto;
import ua.in.kp.dto.project.ProjectResponseDto;
import ua.in.kp.entity.ProjectEntity;

@Mapper(config = MapperConfig.class, uses = TagMapper.class)
public interface ProjectMapper {
    @Mapping(target = "tags", source = "tags", qualifiedByName = "mapTagToStringSet")
    @Mapping(target = "collectDeadline", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "goalDeadline", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "owner.ownerId", source = "owner.id")
    ProjectResponseDto toDto(ProjectEntity projectEntity);

    @Mapping(target = "tags", source = "tags", qualifiedByName = "mapStringToTagSet")
    ProjectEntity toEntity(ProjectCreateRequestDto projectDto);
}
