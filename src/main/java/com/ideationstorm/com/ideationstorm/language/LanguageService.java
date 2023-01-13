package com.ideationstorm.com.ideationstorm.language;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;

    public Language updateLanguage(LanguageUpdateRequest request) {
        return languageRepository.save(Language.builder()
                .id(request.getId())
                .name(request.getName()).build());

    }


}
