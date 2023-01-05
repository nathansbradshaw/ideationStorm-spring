package com.ideationstorm.com.ideationstorm.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ideationstorm.com.ideationstorm.project.ProjectEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    @JsonIgnoreProperties({"projects", "categories" })
    @ManyToMany(mappedBy = "categories")
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
