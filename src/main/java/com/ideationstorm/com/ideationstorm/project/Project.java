package com.ideationstorm.com.ideationstorm.project;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ideationstorm.com.ideationstorm.category.Category;
import com.ideationstorm.com.ideationstorm.language.Language;
import com.ideationstorm.com.ideationstorm.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "projects", schema = "IDEATION_STORM")
public class Project {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    private String title;
    private String description;
    private String content;
    private int difficulty;
    private int score;
    @Column(name="created_datetime")
    @CreationTimestamp
    private LocalDateTime createdDatetime;
    @Column(name="updated_datetime")
    private LocalDateTime updatedDatetime;

    @JsonIgnoreProperties({"languages", "projects"})
    @ManyToMany
    @JoinTable(
            name = "project_languages",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<Language> languages;

    @JsonIgnoreProperties({"projects", "categories" })
    @ManyToMany
    @JoinTable(
            name = "project_categories",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    public ProjectEntity(ProjectEntity project) {
        this.title = project.title;
        this.languages = project.languages;
        this.categories = project.categories;
        this.content = project.content;
        this.description = project.description;
        this.user = project.user;
        this.difficulty = project.difficulty;
    }

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

    public Project() {}

    public Project(String title, String description) {
        this.setTitle(title);
        this.setDescription(description);
    }


    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
