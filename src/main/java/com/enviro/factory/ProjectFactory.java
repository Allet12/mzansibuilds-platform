package com.enviro.factory;

import com.enviro.domain.Project;
import com.enviro.domain.User;
import com.enviro.util.Helper;

public class ProjectFactory {

    public static Project createProject(String name, String description, User user) {

        if (Helper.isNullOrEmpty(name))
            throw new IllegalArgumentException("Project name is required");

        if (!Helper.isValidObject(user))
            throw new IllegalArgumentException("User is required");

        return new Project.Builder()
                .setName(name)
                .setDescription(description)
                .setUser(user)
                .build();
    }
}