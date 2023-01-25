package com.ideationstorm.com.ideationstorm.language;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;

    public List<Language> getAllLanguages(){
        return languageRepository.findAll();
    }

    public Language getLanguageById(long id){
        return languageRepository.findById(id).orElseThrow(() -> new LanguageNotFoundException(id));
    }

    public Language getLanguageByName(String name){
        return languageRepository.findByName(name).orElseThrow(() -> new LanguageNotFoundException(name));
    }

    public Language createLanguage(LanguageCreateRequest request) {
        return languageRepository.save(Language.builder().name(request.getName()).build());
    }

    public Language updateLanguage(LanguageUpdateRequest request) {
        return languageRepository.save(Language.builder()
                .id(request.getId())
                .name(request.getName()).build());

    }




}
