package com.enviro.service;

import com.enviro.domain.Milestone;
import com.enviro.repository.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MilestoneService implements IServiceMilestone {

    private final MilestoneRepository repository;

    @Autowired
    public MilestoneService(MilestoneRepository repository) {
        this.repository = repository;
    }

    @Override
    public Milestone create(Milestone milestone) {
        return repository.save(milestone);
    }

    @Override
    public Milestone read(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Milestone not found"));
    }

    @Override
    public Milestone update(Milestone milestone) {
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Milestone> getByProjectId(Long projectId) {
        return repository.findByProjectId(projectId);
    }

    @Override
    public List<Milestone> getAll() {
        return List.of();
    }
}
