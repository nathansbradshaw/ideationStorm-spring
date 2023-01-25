package com.ideationstorm.com.ideationstorm.language;

public class LanguageNotFoundException extends RuntimeException{
    LanguageNotFoundException(long id) {
        super("Could not find language with id " + id);
    }
    LanguageNotFoundException(String name){
        super("Could not find language with name " + name);
    }
}
