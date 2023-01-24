package com.ideationstorm.com.ideationstorm.auth;

import com.ideationstorm.com.ideationstorm.config.JwtService;
import com.ideationstorm.com.ideationstorm.user.User;
import com.ideationstorm.com.ideationstorm.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;

    private AuthenticationService underTest;

    @BeforeEach
    void setUp() {
        underTest = new AuthenticationService(userRepository, passwordEncoder, jwtService, authenticationManager );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .email("email")
                .username("username")
                .password("password")
                .build();


        AuthenticationResponse authenticationResponse = underTest.register(registerRequest);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser.getEmail()).isEqualTo(registerRequest.getEmail());
        assertThat(capturedUser.getName()).isEqualTo(registerRequest.getUsername());
//        assertThat(capturedUser.getPassword()).isEqualTo(registerRequest.getPassword());

        //TODO expand on this assertion
        assertThat(authenticationResponse).isNotNull();
    }

    @Test
    void authenticate() {
        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .email("email")
                .password("password").build();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(User.builder().build()));
        AuthenticationResponse authenticationResponse = underTest.authenticate(authenticationRequest);

        //TODO expand on this assertion
        assertThat(authenticationResponse).isNotNull();
    }
}