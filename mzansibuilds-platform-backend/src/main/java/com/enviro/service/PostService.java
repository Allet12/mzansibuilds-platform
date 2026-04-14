package com.enviro.service;

import com.enviro.domain.Post;
import com.enviro.domain.Project;
import com.enviro.domain.User;
import com.enviro.repository.PostRepository;
import com.enviro.util.Helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IServicePost {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post create(Post post) {

        if (post == null)
            throw new IllegalArgumentException("Post cannot be null");

        if (!Helper.isValidObject(post.getAuthor()))
            throw new IllegalArgumentException("Author is required");

        if (Helper.isNullOrEmpty(post.getContent()))
            throw new IllegalArgumentException("Content is required");

        if (!Helper.isValidObject(post.getProject()))
            throw new IllegalArgumentException("Project is required");

        return postRepository.save(post);
    }

    @Override
    public Post read(Long id) {

        if (id == null)
            throw new IllegalArgumentException("ID cannot be null");

        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + id));
    }

    @Override
    public Post update(Post post) {

        if (post == null || post.getId() == null)
            throw new IllegalArgumentException("Post or ID cannot be null");

        Post existing = read(post.getId());

        // Only update allowed fields
        if (!Helper.isNullOrEmpty(post.getContent())) {
            existing.setContent(post.getContent());
        }

        return postRepository.save(existing);
    }

    @Override
    public void delete(Long id) {

        if (id == null)
            throw new IllegalArgumentException("ID cannot be null");

        if (!postRepository.existsById(id))
            throw new IllegalArgumentException("Post not found");

        postRepository.deleteById(id);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public List<Post> getPostsByProject(Project project) {

        if (!Helper.isValidObject(project))
            throw new IllegalArgumentException("Project is required");

        return postRepository.findByProject(project);
    }

    public List<Post> getPostsByUser(User user) {

        if (!Helper.isValidObject(user))
            throw new IllegalArgumentException("User is required");

        return postRepository.findByAuthor(user);
    }
    public List<Post> getFeed() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }
}