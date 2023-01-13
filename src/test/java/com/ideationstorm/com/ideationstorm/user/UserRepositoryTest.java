package com.ideationstorm.com.ideationstorm.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserRepositoryTest {


    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindByUsername() {
        User user = User.builder().username("testuser").email("test@example.com").role(Role.USER).password("password").build();
        underTest.save(user);
        Optional<User> exists = underTest.findByEmail("test@example.com");

        assertThat(exists.isPresent()).isTrue();
    }


    @Test
    void itShouldFindByEmail() {
    }
}