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
    private final LanguageService languageService;

    @Autowired
    public LanguageController(LanguageRepository languageRepository, LanguageService languageService){
        this.languageRepository = languageRepository;
        this.languageService = languageService;
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

    @PutMapping("/update")
    public  ResponseEntity<Language> updateLanguage(@RequestBody LanguageUpdateRequest languageUpdateRequest) {
        return ResponseEntity.ok(languageService.updateLanguage(languageUpdateRequest));
    }

}
