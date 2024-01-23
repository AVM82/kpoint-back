package ua.in.kp.mapper;

import org.mapstruct.Mapper;
import ua.in.kp.config.MapperConfig;
import ua.in.kp.dto.ProjectDto;
import ua.in.kp.entity.ProjectEntity;

@Mapper(config = MapperConfig.class)
public interface ProjectMapper {

    ProjectDto projectEntityToProjectDto(ProjectEntity projectEntity);

    ProjectEntity projectDtoToProjectEntity(ProjectDto projectDto);

}
