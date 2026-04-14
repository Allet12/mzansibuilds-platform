package com.enviro.service;

import com.enviro.domain.Project;
import com.enviro.domain.User;
import com.enviro.factory.ProjectFactory;
import com.enviro.factory.UserFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    private Long projectId;

    private Long userId;

    @Test
    @Order(1)
    void create() {

        User user = userService.create(
                UserFactory.createUser(
                        "Bob",
                        "bob@example.com",
                        "password123"
                )
        );

        userId = user.getId();

        Project project = ProjectFactory.createProject(
                "Project One",
                "Description",
                user
        );

        Project created = projectService.create(project);

        assertNotNull(created);
        assertNotNull(created.getId());

        projectId = created.getId();
    }

    @Test
    @Order(2)
    void read() {
        assertNotNull(projectService.read(projectId));
    }

    @Test
    @Order(3)
    void update() {

        Project project = projectService.read(projectId);
        project.setName("Updated Project");

        Project updated = projectService.update(project);

        assertEquals("Updated Project", updated.getName());
    }

//    @Test
//    @Order(4)
//    void delete() {
//
//        projectService.delete(projectId);
//
//        assertThrows(RuntimeException.class,
//                () -> projectService.read(projectId));
//    }
}