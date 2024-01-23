package ua.in.kp.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ua.in.kp.dto.ProjectDto;
import ua.in.kp.entity.ProjectEntity;
import ua.in.kp.mapper.ProjectMapper;
import ua.in.kp.repository.ProjectRepository;

@AllArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectEntity createProject(ProjectDto projectDto) {
        ProjectEntity projectEntity = projectMapper.projectDtoToProjectEntity(projectDto);
        return projectRepository.save(projectEntity);
    }

    public ProjectDto projectToDto(ProjectEntity projectEntity) {
        return projectMapper.projectEntityToProjectDto(projectEntity);
    }
}
