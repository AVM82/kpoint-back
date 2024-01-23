//package ua.in.kp.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import ua.in.kp.dto.ProjectDto;
//import ua.in.kp.entity.ProjectEntity;
//import ua.in.kp.mapper.ProjectMapper;
//import ua.in.kp.repository.ProjectRepository;
//
//
//class ProjectServiceTest {
//
//
//    @Mock
//    ProjectRepository projectRepository;
//    @Mock
//    ProjectMapper projectMapper;
//    ProjectService projectService = new ProjectService(projectRepository, projectMapper);
//
//    @Test
//    void testCreateProject() {
//        ProjectEntity newProject =
//                new ProjectEntity();
//        when(projectRepository.save(newProject)).thenReturn(newProject);
//
//        ProjectDto newProjectDto = new ProjectDto();
//        when(projectMapper.projectEntityToProjectDto(newProject)).thenReturn(newProjectDto);
//
//        ProjectEntity result = projectService.createProject(new ProjectDto());
//        assertEquals(newProject, result);
//    }
//}