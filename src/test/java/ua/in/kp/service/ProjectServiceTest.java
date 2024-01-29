package ua.in.kp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.in.kp.entity.ProjectEntity;
import ua.in.kp.mapper.ProjectMapper;
import ua.in.kp.repository.ProjectRepository;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private ProjectMapper projectMapper;
    @InjectMocks
    private ProjectService projectService;

    @Test
    void getAllProjectsTest() {
        Pageable pageable = Mockito.mock(Pageable.class);
        Page<ProjectEntity> page = Mockito.mock(Page.class);
        Mockito.when(projectRepository.findAll(pageable)).thenReturn(page);
        projectService.getAllProjects(pageable);
        Mockito.verify(projectRepository,Mockito.times(1)).findAll(pageable);
        Mockito.verify(page,Mockito.times(1)).map(any());
    }
}
