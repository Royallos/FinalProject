package com.example.newsportal.service.impl;

import com.example.newsportal.model.User;
import com.example.newsportal.repository.UserRepository;
import com.example.newsportal.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

}
