package com.ideationstorm.com.ideationstorm.project;

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
    private UserEntity user;

    private String title;
    private String description;
    private String content;
    private int difficulty;
    private int score;

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


}
