package com.enviro.service;

import com.enviro.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IServiceUser extends IService<User, Long> {
    List<User> getAll();

    UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException;
}