package com.ideationstorm.com.ideationstorm.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideationstorm.com.ideationstorm.AbstractContainerBaseTest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class CategoryControllerTest extends AbstractContainerBaseTest {




    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

    @BeforeEach
    void setup() {
        categoryRepository.deleteAll();

        Set<Category> categories = new HashSet<>();

        categories.add(Category.builder().name("FRONTEND").id(1).build());
        categories.add(Category.builder().name("BACKEND").id(2).build());
        categories.add(Category.builder().name("FULLSTACK").id(3).build());

        categoryRepository.saveAll(categories);
    }


    @Test
    public void CategoryController_GetAllCategories() throws Exception{
        ResultActions response = mockMvc.perform(get("/categories"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("BACKEND",
                        "FULLSTACK",
                        "FRONTEND"
                )));
    }

    @Test
    public void CategoryController_GetCategoryById() throws  Exception {
        Category item = categoryRepository.findByName("FULLSTACK");

        ResultActions response = mockMvc.perform(get("/categories/{id}", item.getId()));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name", is("FULLSTACK")))
                .andExpect(jsonPath("$.id", is((int) item.getId())));
    }

    @Test
    public void CategoryController_GetCategoryByName() throws  Exception {

        ResultActions response = mockMvc.perform(get("/categories/?name={name}", "FullSTACK"));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name", is("FULLSTACK")));
    }

    @Test
    public void CategoryController_CreateCategory_ReturnCreatedCategory() throws Exception {

        ResultActions response = mockMvc.perform(post("/categories/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CategoryCreateRequest.builder()
                        .name("RPC")
                        .build())
                )
        );

        response.andExpect(MockMvcResultMatchers.status().isCreated());
        response.andExpect(jsonPath("$.name", is("RPC")));
    }


    @Test
    public void CategoryController_UpdateCategory_ReturnUpdatedCategory() throws Exception {
        Category item = categoryRepository.findByName("FULLSTACK");
        ResultActions response = mockMvc.perform(put("/categories/update/{id}", item.getId())
                .contentType(MediaType.APPLICATION_JSON)
                        .content("android app")
        );

        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(jsonPath("$.name", is("android app")));
        response.andExpect(jsonPath("$.id", is((int) item.getId())));
    }

    //TODO uncomment this test when delete is made
//    @Test
//    public void CategoryController_DeleteCategory_ReturnVoid() throws Exception{
//        ResultActions response = mockMvc.perform(delete("/categories/delete/1")
//                .contentType(MediaType.APPLICATION_JSON)
//        );
//
//        response.andExpect(MockMvcResultMatchers.status().isOk());
//
//
//
//
//    }


}