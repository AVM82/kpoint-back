package ua.in.kp.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ua.in.kp.config.MapperConfig;
import ua.in.kp.dto.project.ProjectCreateRequestDto;
import ua.in.kp.dto.project.ProjectResponseDto;
import ua.in.kp.entity.ProjectEntity;

@Mapper(config = MapperConfig.class)
public interface ProjectMapper {
    @Mapping(target = "collectDeadline", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "goalDeadline", dateFormat = "yyyy-MM-dd")
    ProjectResponseDto toDto(ProjectEntity projectEntity);

//    @Mapping(target = "collectDeadline", qualifiedByName = "stringToLocalDateTime")
//    @Mapping(target = "goalDeadline", qualifiedByName = "stringToLocalDateTime")
    ProjectEntity toEntity(ProjectCreateRequestDto projectDto);

//    @Named("stringToLocalDateTime")
//    default LocalDateTime stringToLocalDateTime(String dateTimeString) {
//        if (dateTimeString == null) {
//            return null;
//        }
//        return LocalDateTime.parse(dateTimeString,
//                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//    }
}
