package com.ideationstorm.com.ideationstorm.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectController {
    ProjectRepository projectRepository;
    @Autowired
    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("projects")
    public @ResponseBody List<ProjectEntity> getAllProjects(){
        return projectRepository.findAll();
    }

}
