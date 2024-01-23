package ua.in.kp.controller;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/projects")
public class ProjectController {

  private final ProjectService projectService;

    @PostMapping("/")
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto createdProject, UriComponentsBuilder ucb) {

        ProjectEntity newProject = projectService.createProject(createdProject);

        return new ResponseEntity<>(projectService.projectToDto(newProject), HttpStatus.CREATED);
    }

}
