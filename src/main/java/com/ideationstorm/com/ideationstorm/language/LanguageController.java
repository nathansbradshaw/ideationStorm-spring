package com.ideationstorm.com.ideationstorm.language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LanguageController {
    LanguageRepository languageRepository;

    @Autowired
    public LanguageController(LanguageRepository languageRepository){
        this.languageRepository = languageRepository;
    }

    @GetMapping("languages")
    public @ResponseBody List<LanguageEntity> getAllLanguages() {
        return languageRepository.findAll();
    }
}
