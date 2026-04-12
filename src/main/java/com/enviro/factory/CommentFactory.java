package com.enviro.factory;

import com.enviro.domain.Comment;
import com.enviro.domain.User;
import com.enviro.domain.Post;
import com.enviro.util.Helper;
import java.time.LocalDateTime;

public class CommentFactory {
    public static Comment createComment(Long id, User author, Post post, String text, LocalDateTime createdAt) {
        if (!Helper.isValidId(id) || !Helper.isValidObject(author) || !Helper.isValidObject(post) || Helper.isNullOrEmpty(text) || !Helper.isValidDateTime(createdAt)) {
            return null;
        }

        return new Comment.Builder()
                .setId(id)
                .setAuthor(author)
                .setPost(post)
                .setText(text)
                .setCreatedAt(createdAt)
                .build();
    }
}