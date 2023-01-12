package com.ideationstorm.com.ideationstorm.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("projects")
public class ProjectController {
    ProjectRepository projectRepository;
    @Autowired
    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping()
    public @ResponseBody List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    @PostMapping("create")
    public ResponseEntity<ProjectEntity> createProject(@RequestBody ProjectEntity project){
        try {
            ProjectEntity _project = projectRepository.save(new ProjectEntity(project));
            return new ResponseEntity<>(_project, HttpStatus.CREATED);
        } catch (Exception e) {
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@RequestBody ProjectEntity project, @PathVariable("id") long id){
        try {
            projectRepository.save(project, id);
            return ResponseEntity.ok("Resource Saved");
        } catch (Exception e) {
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
