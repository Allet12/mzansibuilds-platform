package com.enviro.repository;

import com.enviro.domain.CollaborationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaborationRequestRepository extends JpaRepository<CollaborationRequest, Long> {
}