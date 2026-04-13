package com.enviro.service;

import com.enviro.domain.Project;
import com.enviro.domain.User;
import com.enviro.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements IServiceProject {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project create(Project project) {
        if (project == null)
            throw new IllegalArgumentException("Project cannot be null");

        return projectRepository.save(project);
    }

    @Override
    public Project read(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
    }

    @Override
    public Project update(Project project) {

        Project existing = read(project.getId());

        if (project.getName() != null)
            existing.setName(project.getName());

        if (project.getDescription() != null)
            existing.setDescription(project.getDescription());

        return projectRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }
    public List<Project> getProjectsByUser(User user) {
        return projectRepository.findByUser(user);
    }
}