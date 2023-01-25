package com.ideationstorm.com.ideationstorm.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideationstorm.com.ideationstorm.AbstractContainerBaseTest;
import com.ideationstorm.com.ideationstorm.category.Category;
import com.ideationstorm.com.ideationstorm.category.CategoryRepository;
import com.ideationstorm.com.ideationstorm.category.CategoryService;
import com.ideationstorm.com.ideationstorm.language.Language;
import com.ideationstorm.com.ideationstorm.language.LanguageRepository;
import com.ideationstorm.com.ideationstorm.user.Role;
import com.ideationstorm.com.ideationstorm.user.User;
import com.ideationstorm.com.ideationstorm.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ProjectControllerTest extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private  ProjectService projectService;

    @Autowired
    private  ProjectRepository projectRepository;

    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;



    @BeforeEach
    void setUp() {
        categoryRepository.deleteAll();
        projectRepository.deleteAll();
        languageRepository.deleteAll();
        userRepository.deleteAll();

        Set<Category> categories = new HashSet<>();

        categories.add(Category.builder().name("FRONTEND").id(1).build());
        categories.add(Category.builder().name("BACKEND").id(2).build());
        categories.add(Category.builder().name("FULLSTACK").id(3).build());

        categoryRepository.saveAll(categories);


        Set<Language> languages = new HashSet<>();

        languages.add(Language.builder().name("Rust").build());
        languages.add(Language.builder().name("Java").build());
        languages.add(Language.builder().name("TypeScript").build());

        languageRepository.saveAll(languages);

        User mockUser = User.builder()
                .username("TestyMcTestFace")
                .email("test@example.com")
                .password("123456IsAGoodPass")
                .role(Role.USER)
                .build();


        userRepository.save(mockUser);

        Set<Project> projects = new HashSet<>();

        projects.add(Project.builder().title("Project one")
                .score(0)
                .difficulty(1)
                .content("Lorem ipsum dolor")
                .description("Some project")
//                .languages(languages)
//                .categories(categories)
                .user(mockUser)
                .build());

        projects.add(Project.builder().title("Project two")
                .score(0)
                .difficulty(1)
                .content("Lorem ipsum dolor two")
                .description("Some project two")
                .user(mockUser)
                .build());

        projectRepository.saveAll(projects);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void ProjectController_getAllProjects() throws Exception {
        ResultActions response = mockMvc.perform(get("/projects"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].title",
                        containsInAnyOrder("Project one", "Project two")
                ));
    }

    @Test
    @WithMockUser(username = "test@example.com")
    void ProjectController_createProject() throws Exception {



        ResultActions response = mockMvc.perform(post("/projects/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Project.builder().title("Project three")
                        .difficulty(1)
                        .content("Lorem ipsum dolor three")
                        .description("Some project three")

                        .categories(null)
                        .languages(null)
                        .build()))
        );

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.title", is("Project three")))
                .andExpect(jsonPath("$.score", is(0)))
                .andExpect(jsonPath("$.difficulty", is(1)))
                .andExpect(jsonPath("$.content", is("Lorem ipsum dolor three")));


    }

    @Test
    void ProjectController_updateProject() throws Exception {
    }

    @Test
    void ProjectController_assignCategoryToProject() throws Exception {
    }

    @Test
    void ProjectController_assignLanguageToProject() throws Exception {
    }
}