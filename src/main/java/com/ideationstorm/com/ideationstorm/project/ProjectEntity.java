package com.ideationstorm.com.ideationstorm.project;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ideationstorm.com.ideationstorm.language.LanguageEntity;
import com.ideationstorm.com.ideationstorm.user.UserEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "projects", schema = "IDEATION_STORM")
public class ProjectEntity {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity user;

    private String title;
    private String description;
    private String content;
    private int difficulty;
    private int score;

    @JsonIgnoreProperties({"languages", "projects"})
    @ManyToMany
    @JoinTable(
            name = "project_languages",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<LanguageEntity> languages;

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ProjectEntity() {}

    public ProjectEntity(String title, String description) {
        this.setTitle(title);
        this.setDescription(description);
    }


    public long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Set<LanguageEntity> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<LanguageEntity> languages) {
        this.languages = languages;
    }
}
