package com.ideationstorm.com.ideationstorm.language;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ideationstorm.com.ideationstorm.project.ProjectEntity;
import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name="languages")
public class LanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;

    @JsonIgnoreProperties({"languages", "projects"})
    @ManyToMany(mappedBy = "languages")
    private Set<ProjectEntity> projects;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProjectEntity> getProjects() {
        return projects;
    } 

    public void setProjects(Set<ProjectEntity> projects) {
        this.projects = projects;
    }
}
