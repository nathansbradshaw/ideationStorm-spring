package com.ideationstorm.com.ideationstorm.project;

import com.ideationstorm.com.ideationstorm.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("projects")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping()
    public @ResponseBody List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    @PostMapping("create")
    public ResponseEntity<Project> createProject(@RequestBody ProjectCreateRequest project,
                                                 @CurrentSecurityContext(expression = "authentication")
                                                 Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok((projectService.createProject(project, user)));
    }

}
