package com.enviro.service;

import com.enviro.domain.Comment;
import com.enviro.domain.Post;
import com.enviro.domain.Project;
import com.enviro.domain.User;
import com.enviro.factory.CommentFactory;
import com.enviro.factory.PostFactory;
import com.enviro.factory.ProjectFactory;
import com.enviro.factory.UserFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PostService postService;

    private Long commentId;

    @Test
    @Order(1)
    void create() {

        User user = userService.create(
                UserFactory.createUser("Jane", "jane@example.com", "password123")
        );

        Project project = projectService.create(
                ProjectFactory.createProject("Test Project", "Desc", user)
        );

        Post post = postService.create(
                PostFactory.createPost(user, "Post Content", project)
        );

        Comment comment = CommentFactory.createComment(
                user,
                post,
                "First Comment"
        );

        Comment created = commentService.create(comment);

        assertNotNull(created);
        assertNotNull(created.getId());

        commentId = created.getId();
    }

    @Test
    @Order(2)
    void read() {
        assertNotNull(commentService.read(commentId));
    }

    @Test
    @Order(3)
    void update() {

        Comment comment = commentService.read(commentId);
        comment.setText("Updated Comment");

        Comment updated = commentService.update(comment);

        assertEquals("Updated Comment", updated.getText());
    }

    @Test
    @Order(4)
    void delete() {

        commentService.delete(commentId);

        assertThrows(RuntimeException.class,
                () -> commentService.read(commentId));
    }

    @Test
    @Order(5)
    void getAll() {
        assertNotNull(commentService.getAll());
    }
}