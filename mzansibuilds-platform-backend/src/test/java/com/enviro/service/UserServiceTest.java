package com.enviro.service;

import com.enviro.domain.User;
import com.enviro.factory.UserFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    private Long userId;

    @Test
    @Order(1)
    void create() {

        User user = UserFactory.createUser(
                "Charlie",
                "charlie@example.com",
                "password123"
        );

        User created = userService.create(user);

        assertNotNull(created);
        assertNotNull(created.getId());

        userId = created.getId();
    }

    @Test
    @Order(2)
    void read() {
        User user = userService.read(userId);
        assertNotNull(user);
    }

    @Test
    @Order(3)
    void update() {

        User user = userService.read(userId);
        user.setUsername("Updated Charlie");

        User updated = userService.update(user);

        assertEquals("Updated Charlie", updated.getUsername());
    }

    @Test
    @Order(4)
    void validateCredentials() {

        boolean valid = userService.validateCredentials(
                "Updated Charlie",
                "password123"
        );

        assertTrue(valid);
    }

    @Test
    @Order(5)
    void delete() {

        userService.delete(userId);

        assertThrows(RuntimeException.class,
                () -> userService.read(userId));
    }

    @Test
    @Order(5)
    void getAll() {
        assertNotNull(userService.getAll());
    }
}