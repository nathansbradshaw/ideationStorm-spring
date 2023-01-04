//package com.ideationstorm.com.ideationstorm.project_lanuage;
//
//import com.ideationstorm.com.ideationstorm.language.LanguageEntity;
//import com.ideationstorm.com.ideationstorm.project.ProjectEntity;
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "project_Languages")
//public class ProjectLanguageEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    private ProjectEntity project;
//    private LanguageEntity language;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "PROJECT_ID")
//    public ProjectEntity getProject() {
//        return project;
//    }
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "LANGUAGE_ID")
//    public LanguageEntity getLanguage() {
//        return language;
//    }
//
//
//}
