package com.enviro.service;

import com.enviro.domain.User;
import com.enviro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IServiceUser {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {

        if (user == null)
            throw new IllegalArgumentException("User cannot be null");

        if (userRepository.existsByUsername(user.getUsername()))
            throw new IllegalArgumentException("Username already exists");

        if (userRepository.existsByEmail(user.getEmail()))
            throw new IllegalArgumentException("Email already exists");

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        return userRepository.save(user);
    }

    @Override
    public User read(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public User update(User user) {

        User existing = read(user.getId());

        if (user.getUsername() != null)
            existing.setUsername(user.getUsername());

        if (user.getEmail() != null)
            existing.setEmail(user.getEmail());

        if (user.getPassword() != null)
            existing.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        return userRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

    public boolean validateCredentials(String username, String password) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return BCrypt.checkpw(password, user.getPassword());
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}