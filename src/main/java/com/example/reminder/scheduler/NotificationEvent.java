package com.example.reminder.scheduler;

import com.example.reminder.models.notification.NotificationType;

import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class NotificationEvent {
    private String messageId;
    private List<String> chats; // Список ID чатов
    private NotificationType type;
    private String message;
}
