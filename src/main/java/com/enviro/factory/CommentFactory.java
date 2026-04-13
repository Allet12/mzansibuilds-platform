package com.enviro.factory;

import com.enviro.domain.Comment;
import com.enviro.domain.User;
import com.enviro.domain.Post;
import com.enviro.util.Helper;

public class CommentFactory {

    public static Comment createComment(User author, Post post, String text) {

        if (!Helper.isValidObject(author))
            throw new IllegalArgumentException("Author is required");

        if (!Helper.isValidObject(post))
            throw new IllegalArgumentException("Post is required");

        if (Helper.isNullOrEmpty(text))
            throw new IllegalArgumentException("Comment text is required");

        return new Comment.Builder()
                .setAuthor(author)
                .setPost(post)
                .setText(text)
                .build();
    }
}