package com.enviro.repository;

import com.enviro.domain.Post;
import com.enviro.domain.Project;
import com.enviro.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByProject(Project project);

    List<Post> findByAuthor(User author);

    List<Post> findAllByOrderByCreatedAtDesc(); // 🔥 for feed
}