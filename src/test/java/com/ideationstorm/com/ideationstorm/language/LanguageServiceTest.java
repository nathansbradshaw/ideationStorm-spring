package com.ideationstorm.com.ideationstorm.language;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LanguageServiceTest {

    @Mock
    private LanguageRepository languageRepository;
    private LanguageService underTest;

    @BeforeEach
    void setUp() {
        underTest = new LanguageService(languageRepository);
    }

    @Test
    void updateLanguage() {
        LanguageUpdateRequest updateRequest = LanguageUpdateRequest.builder()
                .id(0)
                .name("language")
                .build();

        underTest.updateLanguage(updateRequest);

        ArgumentCaptor<Language> languageArgumentCaptor = ArgumentCaptor.forClass(Language.class);

        verify(languageRepository).save(languageArgumentCaptor.capture());


    }
}