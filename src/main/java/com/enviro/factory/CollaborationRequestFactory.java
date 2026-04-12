package com.enviro.factory;

import com.enviro.domain.CollaborationRequest;
import com.enviro.domain.User;
import com.enviro.domain.Project;
import com.enviro.util.Helper;
import java.time.LocalDateTime;

public class CollaborationRequestFactory {
    public static CollaborationRequest createCollaborationRequest(Long id, User requester, Project project, String message, LocalDateTime requestedAt, boolean approved) {
        if (!Helper.isValidId(id) || !Helper.isValidObject(requester) || !Helper.isValidObject(project) || Helper.isNullOrEmpty(message) || !Helper.isValidDateTime(requestedAt)) {
            return null;
        }

        return new CollaborationRequest.Builder()
                .setId(id)
                .setRequester(requester)
                .setProject(project)
                .setMessage(message)
                .setRequestedAt(requestedAt)
                .setApproved(approved)
                .build();
    }
}