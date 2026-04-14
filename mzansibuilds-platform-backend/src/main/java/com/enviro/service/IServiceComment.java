package com.enviro.service;

import com.enviro.domain.Comment;

import java.util.List;

public interface IServiceComment extends IService<Comment, Long> {
    List<Comment> getAll();
}