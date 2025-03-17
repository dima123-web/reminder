package com.example.reminder.web.controller;

import com.example.reminder.models.notification.Notification;
import com.example.reminder.service.NotificationService;
import com.example.reminder.web.dto.notification.NotificationRequestDto;
import com.example.reminder.web.mappers.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notifications")
@Validated
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;

    @GetMapping
    public ResponseEntity<List<Notification>> getNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotification());
    }

    @PostMapping
    public ResponseEntity<?> createNotification(@RequestBody NotificationRequestDto dto) {
        Notification notification = notificationMapper.toEntity(dto);
        Notification createdNotification = notificationService.create(notification);
        return ResponseEntity.ok(notificationMapper.toDto(createdNotification));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable final Long id) {
        notificationService.delete(id);
    }
}
