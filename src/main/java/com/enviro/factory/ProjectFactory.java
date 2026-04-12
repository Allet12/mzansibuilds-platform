package com.enviro.factory;

import com.enviro.domain.Project;
import com.enviro.domain.User;
import com.enviro.util.Helper;
import java.time.LocalDateTime;

public class ProjectFactory {
    public static Project createProject(Long id, String name, String description, LocalDateTime createdAt, User user) {
        if (!Helper.isValidId(id) || Helper.isNullOrEmpty(name) || Helper.isNullOrEmpty(description) || !Helper.isValidDateTime(createdAt) || !Helper.isValidObject(user)) {
            return null;
        }

        return new Project.Builder()
                .setId(id)
                .setName(name)
                .setDescription(description)
                .setCreatedAt(createdAt)
                .setUser(user)
                .build();
    }
}