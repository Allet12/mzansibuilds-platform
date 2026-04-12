package com.enviro.factory;

import com.enviro.domain.User;
import com.enviro.util.Helper;

public class UserFactory {
    public static User createUser(Long id, String username, String email, String password) {
        if (!Helper.isValidId(id) || Helper.isNullOrEmpty(username) || Helper.isNullOrEmpty(email) || !Helper.isValidEmail(email) || Helper.isNullOrEmpty(password)) {
            return null;
        }

        return new User.Builder()
                .setId(id)
                .setUsername(username)
                .setEmail(email)
                .setPassword(password)
                .build();
    }
}