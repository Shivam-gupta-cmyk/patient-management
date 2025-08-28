package com.shvmdev.authservice.service;

import com.shvmdev.authservice.model.User;
import com.shvmdev.authservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
