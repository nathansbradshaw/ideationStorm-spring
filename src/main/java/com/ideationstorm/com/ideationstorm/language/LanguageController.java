package com.ideationstorm.com.ideationstorm.language;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("languages")
public class LanguageController {
//    LanguageRepository languageRepository;languageRepository
    @Autowired
    private final LanguageService languageService;

    @Autowired
    public LanguageController( LanguageService languageService){
        this.languageService = languageService;
    }

    @GetMapping()
    public ResponseEntity<List<Language>> getAllLanguages() {
      return  new ResponseEntity<>(languageService.getAllLanguages(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable("id") long id){
        return new ResponseEntity<>(languageService.getLanguageById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Language> getLanguageByName(@RequestParam(value="name") String name){
        return new ResponseEntity<>(languageService.getLanguageByName(name), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Language> createLanguage(@RequestBody LanguageCreateRequest request){
            return new ResponseEntity<>(languageService.createLanguage(request), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @RolesAllowed("ADMIN")
    public  ResponseEntity<Language> updateLanguage(@RequestBody LanguageUpdateRequest languageUpdateRequest) {
        return ResponseEntity.ok(languageService.updateLanguage(languageUpdateRequest));
    }

}
