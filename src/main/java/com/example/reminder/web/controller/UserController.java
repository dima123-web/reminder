package com.example.reminder.web.controller;

import com.example.reminder.models.user.User;
import com.example.reminder.service.UserService;
import com.example.reminder.web.dto.user.UserRequestDto;
import com.example.reminder.web.dto.user.UserResponseDto;
import com.example.reminder.web.dto.validation.OnUpdate;
import com.example.reminder.web.mappers.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
@Validated
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PutMapping
    @Operation(summary = "Update user")
    public UserResponseDto update(Long id, @Validated(OnUpdate.class) @RequestBody UserRequestDto dto) {
        User user = userMapper.toEntity(dto);
        User updatedUser = userService.update(user, id);
        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get UserDto by id")
    public UserResponseDto getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }



}
