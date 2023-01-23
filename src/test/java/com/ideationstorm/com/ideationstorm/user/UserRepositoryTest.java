package com.ideationstorm.com.ideationstorm.user;

import com.ideationstorm.com.ideationstorm.AbstractContainerBaseTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
//@AutoConfigureTestDatabase
@ActiveProfiles("test")
class UserRepositoryTest extends AbstractContainerBaseTest {


    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindByUsername() {

        Optional<User> exists = underTest.findByEmail("bob@exmaple.com");

        assertThat(exists.isPresent()).isTrue();
    }


    @Test
    void itShouldFindByEmail() {
    }
}