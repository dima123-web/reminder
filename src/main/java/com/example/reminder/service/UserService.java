package com.example.reminder.service;

import com.example.reminder.models.user.User;

public interface UserService {

    User getByUsername(String username);

    User create(User user);
}
