package com.ideationstorm.com.ideationstorm.language;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ideationstorm.com.ideationstorm.project.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;

    @JsonIgnoreProperties({"languages", "projects"})

    @ManyToMany(mappedBy = "languages",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<Project> projects;

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project){
        projects.add(project);
        project.getLanguages().add(this);
    }

    public void removeProject(Project project){
        projects.remove(project);
        project.getLanguages().remove(this);
    }


}
