package com.enviro.service;

import com.enviro.domain.Milestone;

import java.util.List;

public interface IService<T, ID> {
    T create(T t);
    T read(ID id);

    T update(T t);
    void delete(ID id);

    void delete(Long id);
}