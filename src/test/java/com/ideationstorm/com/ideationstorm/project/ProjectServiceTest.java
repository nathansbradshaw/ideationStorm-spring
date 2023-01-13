package com.ideationstorm.com.ideationstorm.project;

import com.ideationstorm.com.ideationstorm.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;
    private ProjectService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ProjectService(projectRepository);
    }

    @Test
    void canGetAllProjects() {
        underTest.getAllProjects();
        verify(projectRepository).findAll();
    }

    @Test
    @Disabled
    void canCreateProject() {
        User user = User.builder().username("testname").email("test@example.com").password("password").build();
        ProjectCreateRequest request = ProjectCreateRequest.builder()
                .title("Title")
                .content("content")
                .description("desc")
                .difficulty(4)
                .build();
        underTest.createProject(request, user);

        ArgumentCaptor<Project> projectArgumentCaptor = ArgumentCaptor.forClass(Project.class);

        verify(projectRepository).save(projectArgumentCaptor.capture());

        Project capturedProject = projectArgumentCaptor.getValue();

        //TODO this assertion will fail because it's comparing two different types.
        assertThat(capturedProject).isEqualTo(request);
    }

    @Test
    @Disabled
    void updateProject() {
    }
}