package com.enviro.service;

import com.enviro.domain.Project;

import java.util.List;

public interface IServiceProject extends IService<Project, Long> {
    List<Project> getAll();
}