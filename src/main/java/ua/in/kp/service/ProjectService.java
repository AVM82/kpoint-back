package ua.in.kp.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.in.kp.dto.project.CoordinatesDto;
import ua.in.kp.dto.project.ProjectCreateRequestDto;
import ua.in.kp.dto.project.ProjectResponseDto;
import ua.in.kp.entity.ProjectEntity;
import ua.in.kp.exception.ProjectNotFoundException;
import ua.in.kp.mapper.ProjectMapper;
import ua.in.kp.repository.ProjectRepository;

@AllArgsConstructor
@Service
@Slf4j
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectResponseDto createProject(ProjectCreateRequestDto projectDto) {
        log.info("Create project method started");
        ProjectEntity projectEntity = projectMapper.toEntity(projectDto);

        projectRepository.save(projectEntity);
        log.info("ProjectEntity saved, id {}", projectEntity.getProjectId());
        return projectMapper.toDto(projectEntity);
    }

    public List<ProjectResponseDto> getAllProjects(Pageable pageable) {
        Page<ProjectEntity> page = projectRepository.findAll(pageable);
        return page.map(projectMapper::toDto).getContent();
    }

    public ProjectResponseDto getProjectById(String projectId) {
        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ProjectNotFoundException("Project not found with ID: " + projectId));
        ProjectResponseDto dto = projectMapper.toDto(projectEntity);
        dto.setCoordinates(new CoordinatesDto(
                projectEntity.getLatitude(),
                projectEntity.getLongitude()));
        return dto;
    }
}
