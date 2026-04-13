package com.enviro.factory;

import com.enviro.domain.User;
import com.enviro.util.Helper;

public class UserFactory {

    public static User createUser(String username, String email, String password) {

        if (Helper.isNullOrEmpty(username))
            throw new IllegalArgumentException("Username is required");

        if (Helper.isNullOrEmpty(email) || !Helper.isValidEmail(email))
            throw new IllegalArgumentException("Valid email is required");

        if (Helper.isNullOrEmpty(password))
            throw new IllegalArgumentException("Password is required");

        return new User.Builder()
                .setUsername(username)
                .setEmail(email)
                .setPassword(password)
                .build();
    }
}