package com.enviro.service;

import com.enviro.domain.CollaborationRequest;
import com.enviro.domain.Project;
import com.enviro.domain.User;
import com.enviro.repository.CollaborationRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollaborationRequestService implements IServiceCollaborationRequest {

    private final CollaborationRequestRepository repository;

    public CollaborationRequestService(CollaborationRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public CollaborationRequest create(CollaborationRequest request) {
        return repository.save(request);
    }

    @Override
    public CollaborationRequest read(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
    }

    @Override
    public CollaborationRequest update(CollaborationRequest request) {
        CollaborationRequest existing = read(request.getId());

        existing.setApproved(request.isApproved());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CollaborationRequest> getAll() {
        return repository.findAll();
    }

    public List<CollaborationRequest> getByProject(Project project) {
        return repository.findByProject(project);
    }

    public List<CollaborationRequest> getByUser(User user) {
        return repository.findByRequester(user);
    }
}