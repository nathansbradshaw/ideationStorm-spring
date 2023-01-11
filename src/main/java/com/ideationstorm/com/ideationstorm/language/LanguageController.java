package com.ideationstorm.com.ideationstorm.language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("languages/create")
    public ResponseEntity<LanguageEntity> createLanguage(@RequestBody String name){
        try {
            LanguageEntity _language = languageRepository.save(new LanguageEntity(name  ));
            return new ResponseEntity<>(_language, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
