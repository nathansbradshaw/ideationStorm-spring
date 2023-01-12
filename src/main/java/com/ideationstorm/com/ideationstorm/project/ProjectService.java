package com.ideationstorm.com.ideationstorm.project;

import com.ideationstorm.com.ideationstorm.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public Project createProject(ProjectCreateRequest request, User user){
        Project project = Project.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .content(request.getContent())
                .difficulty(request.getDifficulty())
                .languages(request.getLanguages())
                .categories(request.getCategories())
                .user(user)
                .build();
        projectRepository.save(project);
        return project;
    }

}
