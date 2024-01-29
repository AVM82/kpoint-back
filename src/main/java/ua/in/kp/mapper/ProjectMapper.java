package ua.in.kp.mapper;

import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.in.kp.config.MapperConfig;
import ua.in.kp.dto.project.ProjectCreateRequestDto;
import ua.in.kp.dto.project.ProjectResponseDto;
import ua.in.kp.entity.ProjectEntity;
import ua.in.kp.entity.TagEntity;

@Mapper(config = MapperConfig.class, uses = TagMapper.class)
public interface ProjectMapper {
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "collectDeadline", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "goalDeadline", dateFormat = "yyyy-MM-dd")
    ProjectResponseDto toDto(ProjectEntity projectEntity);

    @Mapping(target = "tags", source = "tags")
    ProjectEntity toEntity(ProjectCreateRequestDto projectDto);

//    Set<String> mapTagsToStrings(Set<TagEntity> tags);
//
//    default String mapTagToString(TagEntity tag) {
//        return tag.getName();
//    }

//    default String mapTagToEntity(String tag) {
//        return tag.getName();
//    }
}
