package com.ideationstorm.com.ideationstorm.project;

import com.ideationstorm.com.ideationstorm.category.CategoryRepository;
import com.ideationstorm.com.ideationstorm.language.Language;
import com.ideationstorm.com.ideationstorm.language.LanguageRepository;
import com.ideationstorm.com.ideationstorm.user.User;
import com.ideationstorm.com.ideationstorm.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private LanguageRepository languageRepository;

    @Mock
    private UserRepository userRepository;

    private ProjectService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ProjectService(projectRepository, categoryRepository, languageRepository, userRepository);
    }

    @Test
    void canGetAllProjects() {
        underTest.getAllProjects();
        verify(projectRepository).findAll();
    }

    @Test
    void canCreateProject() {
        User user = User.builder().username("testname").email("test@example.com").password("password").build();

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.ofNullable(user));

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
        assertThat(capturedProject.getTitle()).isEqualTo(request.getTitle());
        assertThat(capturedProject.getContent()).isEqualTo(request.getContent());
        assertThat(capturedProject.getDescription()).isEqualTo(request.getDescription());

    }

    @Test
    void updateProject() {
        User user = User.builder().username("testname").email("test@example.com").password("password").build();


        ProjectUpdateRequest updateRequest = ProjectUpdateRequest.builder()
                .id(1)
                .title("New title")
                .content("new content")
                .description("new description")
                .difficulty(1).build();
        underTest.updateProject(updateRequest, user);

        ArgumentCaptor<Project> projectArgumentCaptor = ArgumentCaptor.forClass(Project.class);
        verify(projectRepository).save(projectArgumentCaptor.capture());
        Project updatedProject = projectArgumentCaptor.getValue();

        assertThat(updatedProject.getDifficulty()).isEqualTo(1);
    }
}