package com.enviro.repository;

import com.enviro.domain.CollaborationRequest;
import com.enviro.domain.Project;
import com.enviro.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollaborationRequestRepository extends JpaRepository<CollaborationRequest, Long> {

    List<CollaborationRequest> findByProject(Project project);

    List<CollaborationRequest> findByRequester(User requester);

    List<CollaborationRequest> findByApproved(boolean approved);
}