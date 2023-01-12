package com.ideationstorm.com.ideationstorm.language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("languages")
public class LanguageController {
    LanguageRepository languageRepository;

    @Autowired
    public LanguageController(LanguageRepository languageRepository){
        this.languageRepository = languageRepository;
    }

    @GetMapping()
    public @ResponseBody List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Language> createLanguage(@RequestBody String name){
        try {
            Language _language = languageRepository.save(new Language(name  ));
            return new ResponseEntity<>(_language, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
