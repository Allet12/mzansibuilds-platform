package com.enviro.factory;

import com.enviro.domain.CollaborationRequest;
import com.enviro.domain.User;
import com.enviro.domain.Project;
import com.enviro.util.Helper;

public class CollaborationRequestFactory {

    public static CollaborationRequest createRequest(User requester, Project project, String message) {

        if (!Helper.isValidObject(requester))
            throw new IllegalArgumentException("Requester is required");

        if (!Helper.isValidObject(project))
            throw new IllegalArgumentException("Project is required");

        if (Helper.isNullOrEmpty(message))
            throw new IllegalArgumentException("Message is required");

        return new CollaborationRequest.Builder()
                .setRequester(requester)
                .setProject(project)
                .setMessage(message)
                .setApproved(false) // default
                .build();
    }
}