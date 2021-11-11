package com.loginregistration.service;

import com.loginregistration.model.User;
import com.loginregistration.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }
}
