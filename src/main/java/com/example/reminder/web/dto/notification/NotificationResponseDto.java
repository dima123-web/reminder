package com.example.reminder.web.dto.notification;

import com.example.reminder.models.notification.Frequency;
import com.example.reminder.models.notification.NotificationType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class NotificationResponseDto {

    private Long id;
    private String name;
    private String description;
    private List<Long> chatIds; // первичный ключ чатов
    private LocalDateTime sendDateTime;
    private Frequency frequency;
    private NotificationType notificationType;

}
