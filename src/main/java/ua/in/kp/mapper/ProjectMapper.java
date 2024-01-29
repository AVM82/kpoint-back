package ua.in.kp.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.in.kp.config.MapperConfig;
import ua.in.kp.dto.project.GetAllProjectsDto;
import ua.in.kp.dto.project.ProjectCreateRequestDto;
import ua.in.kp.dto.project.ProjectResponseDto;
import ua.in.kp.entity.ProjectEntity;
import ua.in.kp.entity.TagEntity;

@Mapper(config = MapperConfig.class)
public interface ProjectMapper {
    @Mapping(target = "collectDeadline", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "goalDeadline", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "owner.ownerId", source = "owner.id")
    ProjectResponseDto toDto(ProjectEntity projectEntity);

    ProjectEntity toEntity(ProjectCreateRequestDto projectDto);

    GetAllProjectsDto getAllToDto(ProjectEntity projectEntity);

    default Set<TagEntity> mapStringToTagSet(Set<String> tags) {
        return tags.stream()
                .map(tagName -> {
                    TagEntity tag = new TagEntity();
                    tag.setName(tagName.toLowerCase());
                    return tag;
                })
                .collect(Collectors.toSet());
    }

    default Set<String> mapTagToStringSet(Set<TagEntity> tags) {
        return tags.stream()
                .map(tag -> tag.getName().toLowerCase())
                .collect(Collectors.toSet());
    }
}
