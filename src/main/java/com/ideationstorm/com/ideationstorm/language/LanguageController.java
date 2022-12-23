package com.ideationstorm.com.ideationstorm.language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguageController {
    LanguageRepository languageRepository;

    @Autowired
    public LanguageController(LanguageRepository languageRepository){
        this.languageRepository = languageRepository;
    }
}
