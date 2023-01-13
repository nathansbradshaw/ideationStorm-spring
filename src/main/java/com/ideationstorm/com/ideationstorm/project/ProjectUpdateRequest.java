package com.ideationstorm.com.ideationstorm.project;

import com.ideationstorm.com.ideationstorm.category.Category;
import com.ideationstorm.com.ideationstorm.language.Language;
import com.ideationstorm.com.ideationstorm.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUpdateRequest {
    private long id;
    private String title;
    private String description;
    private String content;
    private int difficulty;
    private Set<Language> languages;
    private Set<Category> categories;
    private User user;
}
