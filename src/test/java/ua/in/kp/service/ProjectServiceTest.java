package ua.in.kp.service;

import ua.in.kp.dto.project.ProjectCreateRequestDto;
import ua.in.kp.dto.project.ProjectResponseDto;
import ua.in.kp.entity.ProjectEntity;
import ua.in.kp.entity.UserEntity;
import ua.in.kp.exception.ProjectNotFoundException;
import ua.in.kp.mapper.ProjectMapper;
import ua.in.kp.repository.ProjectRepository;
import ua.in.kp.repository.TagRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private ProjectMapper projectMapper;
    @Mock
    private UserService userService;
    @Mock
    private TagRepository tagRepository;
    @InjectMocks
    private ProjectService projectService;

    @Test
    void getAllProjectsTest() {
        Pageable pageable = Mockito.mock(Pageable.class);
        Page<ProjectEntity> page = Mockito.mock(Page.class);
        when(projectRepository.findAll(pageable)).thenReturn(page);
        projectService.getAllProjects(pageable);
        verify(projectRepository, times(1)).findAll(pageable);
        verify(page, times(1)).map(any());
    }

    @Test
    void getProjectById_shouldReturnProjectDto_whenProjectExists() {
        String projectId = "id";
        ProjectEntity mockProjectEntity = new ProjectEntity();
        ProjectResponseDto mockProjectDto = new ProjectResponseDto();

        when(projectRepository.findBy(projectId)).thenReturn(Optional.of(mockProjectEntity));
        when(projectMapper.toDto(mockProjectEntity)).thenReturn(mockProjectDto);

        ProjectResponseDto resultDto = projectService.getProjectById(projectId);

        assertNotNull(resultDto);
        assertEquals(mockProjectDto, resultDto);
        verify(projectRepository).findBy(projectId);
        verify(projectMapper).toDto(mockProjectEntity);
    }

    @Test
    void getProjectById_shouldThrowException_whenProjectDoesNotExist() {
        String projectId = "id";

        when(projectRepository.findBy(projectId)).thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () -> {
            projectService.getProjectById(projectId);
        });

        verify(projectRepository).findBy(projectId);
        verify(projectMapper, never()).toDto(any());
    }

    @Test
    void createProjectTest() {

        ProjectEntity projectEntity = new ProjectEntity();
        ProjectCreateRequestDto projectCreateRequestDto = new ProjectCreateRequestDto();
        projectCreateRequestDto.setTags(Set.of("tag"));

        mockCommonMethodsForCreateProjectTest(projectEntity);

        when(projectMapper.toDto(projectEntity)).thenReturn(new ProjectResponseDto());

        ProjectResponseDto result = projectService.createProject(projectCreateRequestDto);

        assertNotNull(result);
        verify(userService, times(1)).getAuthenticated();
        verify(projectMapper, times(1)).toEntity(projectCreateRequestDto);
        verify(projectRepository, times(1)).save(projectEntity);
        verify(projectMapper, times(1)).toDto(projectEntity);

    }

    @Test
    void createProject_testDefaultLatitude() {

        ProjectEntity projectEntity = new ProjectEntity();
        ProjectCreateRequestDto projectCreateRequestDto = new ProjectCreateRequestDto();
        projectCreateRequestDto.setTags(Set.of("tag"));

        ProjectResponseDto projectResponseDto = new ProjectResponseDto();
        projectResponseDto.setLatitude(49.1);

        mockCommonMethodsForCreateProjectTest(projectEntity);
        when(projectMapper.toDto(projectEntity)).thenReturn(projectResponseDto);
        ProjectResponseDto result = projectService.createProject(projectCreateRequestDto);

        assertEquals(result.getLatitude(), projectEntity.getLatitude());
    }

    private void mockCommonMethodsForCreateProjectTest(ProjectEntity projectEntity) {
        UserEntity user = new UserEntity();

        when(projectMapper.toEntity(any())).thenReturn(projectEntity);
        when(userService.getAuthenticated()).thenReturn(user);
        when(projectRepository.save(projectEntity)).thenReturn(projectEntity);
    }
}
