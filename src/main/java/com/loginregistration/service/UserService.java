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

    public User saveUser(User user) {
        User user2 = userRepository.findUserByEmail(user.getEmail()).get();
        user2.setFirstName(user.getFirstName());
        user2.setLastName(user.getLastName());
        user2.setEmail(user.getEmail());
        user2.setMobileNo(user.getMobileNo());
        user2.setPassword(user.getPassword());
        return userRepository.save(user2);
    }
}
