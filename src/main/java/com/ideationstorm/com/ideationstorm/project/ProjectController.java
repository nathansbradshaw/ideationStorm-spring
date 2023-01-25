package com.ideationstorm.com.ideationstorm.project;

import com.ideationstorm.com.ideationstorm.user.User;
import com.ideationstorm.com.ideationstorm.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectController(ProjectService projectService,
                             ProjectRepository projectRepository,
                             UserRepository userRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Project>> getAllProjects(){
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody ProjectCreateRequest project,
                                                 @CurrentSecurityContext(expression = "authentication")
                                                 Authentication authentication,
                                                 @AuthenticationPrincipal UserDetails userDetails
    ){
        //TODO while this works, it is not ideal and need to be looked at.
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
//        User user = authentication.getPrincipal();
        return new ResponseEntity<>(projectService.createProject(project, user), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable long id, @RequestBody ProjectUpdateRequest request, @CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Optional<Project> project = projectRepository.findById(request.getId());
        if(project.get().getUser() != user){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok(projectService.updateProject(request));
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
