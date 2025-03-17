package com.example.reminder.service.impl;


import com.example.reminder.exception.ResourceNotFoundException;
import com.example.reminder.models.user.User;
import com.example.reminder.repository.UserRepository;
import com.example.reminder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }

    @Override
    public User create(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new IllegalStateException("User already exists.");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }
}
