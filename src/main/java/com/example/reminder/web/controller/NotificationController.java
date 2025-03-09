package com.example.reminder.web.controller;

import com.example.reminder.models.notification.Notification;
import com.example.reminder.service.NotificationService;
import com.example.reminder.web.dto.notification.NotificationRequestDto;
import com.example.reminder.web.dto.notification.NotificationResponseDto;
import com.example.reminder.web.dto.validation.OnCreate;
import com.example.reminder.web.dto.validation.OnUpdate;
import com.example.reminder.web.mappers.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notification")
@Validated
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;

    @GetMapping("/{id}")
    public NotificationResponseDto getById(@PathVariable Long id) {
        Notification notification = notificationService.getById(id);
        return notificationMapper.toDto(notification);

    }

    @PostMapping
    public NotificationResponseDto create(@Validated(OnCreate.class) @RequestBody NotificationRequestDto dto) {
        Notification notification = notificationMapper.toEntity(dto);
        Notification createdNotification = notificationService.create(notification);
        return notificationMapper.toDto(createdNotification);
    }

    @PutMapping
    public NotificationResponseDto update(@Validated(OnUpdate.class) @RequestBody NotificationRequestDto dto,
                                          @PathVariable Long id) {

        Notification notification = notificationMapper.toEntity(dto);
        Notification updatedNotification = notificationService.update(notification, id);
        return notificationMapper.toDto(updatedNotification);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable final Long id) {
        notificationService.delete(id);
    }
}
