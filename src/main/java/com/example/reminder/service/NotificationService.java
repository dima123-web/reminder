package com.example.reminder.service;


import com.example.reminder.models.notification.Notification;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationService {

    Notification getById(Long id);

    Notification create(Notification notification);

    Notification update(Notification notification, Long id);

    void delete(Long id);

    List<Notification> getDueNotifications(LocalDateTime currentTime);

    void updateNotificationSchedule(Notification notification, LocalDateTime newTime);

    void deleteOneTimeNotification(Notification notification);

    List<Notification> getAllNotification();

}
