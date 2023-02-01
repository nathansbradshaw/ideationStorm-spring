package com.ideationstorm.com.ideationstorm.project;

import com.ideationstorm.com.ideationstorm.user.User;
import com.ideationstorm.com.ideationstorm.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("projects")
public class ProjectController {
    private final ProjectService projectService;


    @Autowired
    public ProjectController(ProjectService projectService,
                             ProjectRepository projectRepository,
                             UserRepository userRepository) {
        this.projectService = projectService;
    }

    @GetMapping()
    public ResponseEntity<List<Project>> getAllProjects(){
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody ProjectCreateRequest project,
                                                 @AuthenticationPrincipal UserDetails userDetails
    ){
        return new ResponseEntity<>(projectService.createProject(project, userDetails), HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated")
    @PutMapping("/update/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable long id, @RequestBody ProjectUpdateRequest request,
                                                 @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(projectService.updateProject(request, userDetails));
    }

    @PutMapping("{projectId}/category/{categoryId}")
    public Project assignCategoryToProject(
            @PathVariable long projectId,
            @PathVariable long categoryId
    ) {
        return projectService.assignCategoryToProject(projectId, categoryId);
    }

    @PutMapping("{projectId}/language/{languageId}")
    public Project assignLanguageToProject(
            @PathVariable long projectId,
            @PathVariable long languageId
    ) {
        return projectService.assignLanguageToProject(projectId, languageId);
    }

}
