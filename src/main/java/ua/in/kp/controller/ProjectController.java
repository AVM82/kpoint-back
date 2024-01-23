package ua.in.kp.controller;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ua.in.kp.dto.ProjectDto;
import ua.in.kp.entity.ProjectEntity;
import ua.in.kp.mapper.ProjectMapper;
import ua.in.kp.service.ProjectService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

  private final ProjectService projectService;
  private final ProjectMapper projectMapper;

    @PostMapping("/")
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto createdProject, UriComponentsBuilder ucb) {

        ProjectEntity newProject = projectService.createProject(createdProject);

        URI locationOfNewProject = ucb
                .path("projects/{id}")
                .buildAndExpand(newProject.getProjectId())
                .toUri();
        return ResponseEntity.created(locationOfNewProject).body(projectMapper.projectEntityToProjectDto(newProject));
    }

}
