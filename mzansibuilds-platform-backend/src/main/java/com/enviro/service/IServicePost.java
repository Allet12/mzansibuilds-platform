package com.enviro.service;

import com.enviro.domain.Post;

import java.util.List;

public interface IServicePost extends IService<Post, Long> {
    List<Post> getAll();
}