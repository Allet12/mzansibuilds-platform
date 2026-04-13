package com.enviro.repository;

import com.enviro.domain.Project;
import com.enviro.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByUser(User user);

    List<Project> findByNameContainingIgnoreCase(String name);
}