package com.ideationstorm.com.ideationstorm.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideationstorm.com.ideationstorm.AbstractContainerBaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class CategoryControllerTest  extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

//    @MockBean
    @Autowired
    private CategoryService categoryService;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void CategoryController_CreateCategory_ReturnCreatedCategory() throws Exception {

        ResultActions response = mockMvc.perform(post("/categories/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CategoryCreateRequest.builder()
                        .name("rust")
                        .build())
                )
        );

        response.andExpect(MockMvcResultMatchers.status().isCreated());
        response.andExpect(jsonPath("$.name", is("rust")));



    }


}