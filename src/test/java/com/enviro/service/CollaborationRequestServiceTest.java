package com.enviro.service;

import com.enviro.domain.CollaborationRequest;
import com.enviro.domain.Project;
import com.enviro.domain.User;
import com.enviro.factory.CollaborationRequestFactory;
import com.enviro.factory.ProjectFactory;
import com.enviro.factory.UserFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CollaborationRequestServiceTest {

    @Autowired
    private CollaborationRequestService service;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    private Long requestId;

    @Test
    @Order(1)
    void create() {

        User user = userService.create(
                UserFactory.createUser("John", "john@example.com", "password123")
        );

        Project project = projectService.create(
                ProjectFactory.createProject("Project", "Desc", user)
        );

        CollaborationRequest request = CollaborationRequestFactory.createRequest(
                user,
                project,
                "Please collaborate"
        );

        CollaborationRequest created = service.create(request);

        assertNotNull(created);
        assertNotNull(created.getId());

        requestId = created.getId();
    }

    @Test
    @Order(2)
    void read() {
        assertNotNull(service.read(requestId));
    }

    @Test
    @Order(3)
    void update() {

        CollaborationRequest req = service.read(requestId);
        req.setApproved(true);

        CollaborationRequest updated = service.update(req);

        assertTrue(updated.isApproved());
    }

    @Test
    @Order(4)
    void delete() {

        service.delete(requestId);

        assertThrows(RuntimeException.class,
                () -> service.read(requestId));
    }

    @Test
    @Order(5)
    void getAll() {
        assertNotNull(service.getAll());
    }
}