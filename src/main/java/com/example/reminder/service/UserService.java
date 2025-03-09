package com.example.reminder.service;

import com.example.reminder.models.user.User;

public interface UserService {

    User getById(Long id);

    User getByUsername(String username);

    User update(User user, Long id);

    User create(User user);

    void delete(Long id);
}
