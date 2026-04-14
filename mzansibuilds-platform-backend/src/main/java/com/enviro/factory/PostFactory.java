package com.enviro.factory;

import com.enviro.domain.Post;
import com.enviro.domain.User;
import com.enviro.domain.Project;
import com.enviro.util.Helper;

public class PostFactory {

    public static Post createPost(User author, String content, Project project) {

        if (!Helper.isValidObject(author))
            throw new IllegalArgumentException("Author is required");

        if (Helper.isNullOrEmpty(content))
            throw new IllegalArgumentException("Content is required");

        if (!Helper.isValidObject(project))
            throw new IllegalArgumentException("Project is required");

        return new Post.Builder()
                .setAuthor(author)
                .setContent(content)
                .setProject(project)
                .build();
    }
}