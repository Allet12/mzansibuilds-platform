package com.enviro.service;

import com.enviro.domain.CollaborationRequest;

import java.util.List;

public interface IServiceCollaborationRequest extends IService<CollaborationRequest, Long> {
    List<CollaborationRequest> getAll();
}