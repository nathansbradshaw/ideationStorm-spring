package com.ideationstorm.com.ideationstorm.language;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideationstorm.com.ideationstorm.AbstractContainerBaseTest;
import com.ideationstorm.com.ideationstorm.category.Category;
import com.ideationstorm.com.ideationstorm.category.CategoryCreateRequest;
import com.ideationstorm.com.ideationstorm.category.CategoryRepository;
import com.ideationstorm.com.ideationstorm.category.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class LanguageControllerTest extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        languageRepository.deleteAll();

        Set<Language> languages = new HashSet<>();

        languages.add(Language.builder().name("Rust").build());
        languages.add(Language.builder().name("Java").build());
        languages.add(Language.builder().name("TypeScript").build());

        languageRepository.saveAll(languages);
    }

    @AfterEach
    void tearDown() {
        languageRepository.deleteAll();
    }

    @Test
    void LanguageController_getAllLanguages_ReturnListOfLanguages() throws Exception {
        ResultActions response = mockMvc.perform(get("/languages"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("Rust",
                        "Java",
                        "TypeScript"
                )));
    }

    @Test
    void LanguageController_getAllLanguages_ReturnListOfLanguages_whenMoreLanguagesAreAdded() throws Exception {
        languageRepository.save(Language.builder().name("R").build());
        ResultActions response = mockMvc.perform(get("/languages"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("Rust",
                        "Java",
                        "TypeScript",
                        "R"
                )));
    }


    @Test
    void LanguageController_getLanguageById_ReturnLanguage() throws Exception {
        Language item = languageRepository.findByName("Rust").get();

        ResultActions response = mockMvc.perform(get("/languages/{id}", item.getId()));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name", is("Rust")))
                .andExpect(jsonPath("$.id", is((int) item.getId())));

    }

    @Test
    void LanguageController_getLanguageByName_ReturnLanguage() throws Exception{
        ResultActions response = mockMvc.perform(get("/languages/?name={name}", "Rust"));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name", is("Rust")));

    }

    @Test
    void LanguageController_createLanguage_ReturnCreatedLanguage() throws Exception {
        ResultActions response = mockMvc.perform(post("/languages/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CategoryCreateRequest.builder()
                        .name("c++")
                        .build())
                )
        );

        response.andExpect(MockMvcResultMatchers.status().isCreated());
        response.andExpect(jsonPath("$.name", is("c++")));
    }

    @Test
    void LanguageController_updateLanguage_ReturnUpdatedLanguage() throws Exception {
        Language item = languageRepository.findByName("TypeScript").get();
        ResultActions response = mockMvc.perform(put("/languages/update", item.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(LanguageUpdateRequest.builder().name("JavaScript").id(item.getId()).build()))
        );

        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(jsonPath("$.name", is("JavaScript")));
        response.andExpect(jsonPath("$.id", is((int) item.getId())));
    }

}