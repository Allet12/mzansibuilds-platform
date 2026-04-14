package com.enviro.service;

import com.enviro.domain.Milestone;
import com.enviro.domain.Post;

import java.util.List;

public interface IServiceMilestone extends IService<Milestone, Long>{
    List<Milestone> getAll();
}
