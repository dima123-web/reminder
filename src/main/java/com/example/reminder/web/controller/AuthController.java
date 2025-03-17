package com.example.reminder.web.controller;

import com.example.reminder.models.user.User;
import com.example.reminder.service.UserService;
import com.example.reminder.web.dto.auth.JwtResponse;
import com.example.reminder.web.dto.user.UserRequestDto;
import com.example.reminder.web.mappers.UserMapper;
import com.example.reminder.web.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDto request) {
        // Аутентификация
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Неверное имя пользователя или пароль");
        }

        // Генерация токена
        String token = jwtUtils.generateToken(request.getUsername());

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDto request) {
        User user = userMapper.toEntity(request);
        userService.create(user);
        return ResponseEntity.ok("User registered");
    }

}
