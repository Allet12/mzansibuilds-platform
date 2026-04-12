package com.enviro.factory;

import com.enviro.domain.Post;
import com.enviro.domain.User;
import com.enviro.domain.Project;
import com.enviro.util.Helper;
import java.time.LocalDateTime;

public class PostFactory {
    public static Post createPost(Long id, User author, String content, LocalDateTime createdAt, Project project) {
        if (!Helper.isValidId(id) || !Helper.isValidObject(author) || Helper.isNullOrEmpty(content) || !Helper.isValidDateTime(createdAt) || !Helper.isValidObject(project)) {
            return null;
        }

        return new Post.Builder()
                .setId(id)
                .setAuthor(author)
                .setContent(content)
                .setCreatedAt(createdAt)
                .setProject(project)
                .build();
    }
}