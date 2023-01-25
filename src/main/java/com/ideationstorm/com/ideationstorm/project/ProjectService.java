package com.ideationstorm.com.ideationstorm.project;

import com.ideationstorm.com.ideationstorm.category.Category;
import com.ideationstorm.com.ideationstorm.category.CategoryRepository;
import com.ideationstorm.com.ideationstorm.language.Language;
import com.ideationstorm.com.ideationstorm.language.LanguageRepository;
import com.ideationstorm.com.ideationstorm.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProjectService {

    @Autowired
    private final ProjectRepository projectRepository;

    @Autowired
    private final CategoryRepository categoryRepository;
    private final LanguageRepository languageRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(ProjectCreateRequest request, User user) {
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

    public Project updateProject(ProjectUpdateRequest request) {
        return projectRepository.save(Project.builder()
                .title(request.getTitle())
                .id(request.getId())
                .categories(request.getCategories())
                .content(request.getContent())
                .difficulty(request.getDifficulty())
                .languages(request.getLanguages())
                .build());
    }

    public Project assignCategoryToProject(long projectId, long categoryId) {
        Set<Category> categorySet = null;
        Project project = projectRepository.findById(projectId).get();
        Category category = categoryRepository.findById(categoryId).get();

        categorySet = project.getCategories();
        categorySet.add(category);

        project.setCategories(categorySet);
        return projectRepository.save(project);

    }

    public Project assignLanguageToProject(long projectId, long languageId) {
        Set<Language> languageSet = null;
        Project project =  projectRepository.findById(projectId).get();
        Language language = languageRepository.findById(languageId).get();

        languageSet = project.getLanguages();
        languageSet.add(language);

        project.setLanguages(languageSet);
        return projectRepository.save(project);

    }
}
