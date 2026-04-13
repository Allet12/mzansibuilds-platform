package com.enviro.service;

import com.enviro.domain.Post;
import com.enviro.domain.Project;
import com.enviro.domain.User;
import com.enviro.factory.PostFactory;
import com.enviro.factory.ProjectFactory;
import com.enviro.factory.UserFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    private Long postId;

    @Test
    @Order(1)
    void create() {

        User user = userService.create(
                UserFactory.createUser(
                        "Alice",
                        "alice@example.com",
                        "password123"
                )
        );

        Project project = projectService.create(
                ProjectFactory.createProject(
                        "Test Project",
                        "Description",
                        user
                )
        );

        Post post = PostFactory.createPost(
                user,
                "First Post Content",
                project
        );

        Post created = postService.create(post);

        assertNotNull(created);
        assertNotNull(created.getId());

        postId = created.getId();
    }

    @Test
    @Order(2)
    void read() {
        assertNotNull(postService.read(postId));
    }

    @Test
    @Order(3)
    void update() {

        Post post = postService.read(postId);
        post.setContent("Updated Content");

        Post updated = postService.update(post);

        assertEquals("Updated Content", updated.getContent());
    }

    @Test
    @Order(4)
    void delete() {

        postService.delete(postId);

        assertThrows(RuntimeException.class,
                () -> postService.read(postId));
    }

    @Test
    @Order(5)
    void getAll() {
        assertNotNull(postService.getAll());
    }
}