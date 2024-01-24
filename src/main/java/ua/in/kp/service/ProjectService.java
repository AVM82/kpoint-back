package ua.in.kp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.in.kp.dto.project.ProjectCreateRequestDto;
import ua.in.kp.dto.project.ProjectResponseDto;
import ua.in.kp.entity.ProjectEntity;
import ua.in.kp.mapper.ProjectMapper;
import ua.in.kp.repository.ProjectRepository;

@AllArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectResponseDto createProject(ProjectCreateRequestDto projectDto) {
        ProjectEntity projectEntity = projectMapper.toEntity(projectDto);
        projectRepository.save(projectEntity);
        return projectMapper.toDto(projectEntity);
    }
}
