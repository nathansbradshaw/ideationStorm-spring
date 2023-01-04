package com.ideationstorm.com.ideationstorm.language;

import com.ideationstorm.com.ideationstorm.project.ProjectEntity;
import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name="languages")
public class LanguageEntity {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    @ManyToMany(mappedBy = "languages")
    private Set<ProjectEntity> projects;

}
