package ua.in.kp.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.in.kp.dto.project.ProjectCreateRequestDto;
import ua.in.kp.dto.project.ProjectResponseDto;
import ua.in.kp.service.ProjectService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/")
    public ResponseEntity<ProjectResponseDto> createProject(
            @Valid @RequestBody ProjectCreateRequestDto createdProject) {
        return new ResponseEntity<>(projectService
                .createProject(createdProject), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> findById(@PathVariable String id) {
        return new ResponseEntity<>(projectService.getById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects(Pageable pageable) {
        return projectService.getAllProjects(pageable);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(@PathVariable String id,
            @Valid @RequestBody ProjectCreateRequestDto createdProject) {
        return new ResponseEntity<>(projectService
                .updateProject(id, createdProject), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> deleteProject(@PathVariable String id) {
        projectService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
