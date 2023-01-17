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
import java.util.Optional;

@RestController
@RequestMapping("projects")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectController(ProjectService projectService,
                             ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
    }

    @GetMapping()
    public @ResponseBody List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    @PostMapping()
    public ResponseEntity<Project> createProject(@RequestBody ProjectCreateRequest project,
                                                 @CurrentSecurityContext(expression = "authentication")
                                                 Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok((projectService.createProject(project, user)));
    }

    @PutMapping("/update")
    public ResponseEntity<Project> updateProject(@RequestBody ProjectUpdateRequest request, @CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Optional<Project> project = projectRepository.findById(request.getId());
        if(project.get().getUser() != user){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok(projectService.updateProject(request));
    }

}
