package com.example.reminder.service.impl;

import com.example.reminder.exception.ResourceNotFoundException;
import com.example.reminder.models.notification.Notification;
import com.example.reminder.repository.NotificationRepository;
import com.example.reminder.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification getById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
    }

    @Override
    public Notification create(Notification notification) {
        notificationRepository.save(notification);
        return notification;
    }

    @Override
    public Notification update(Notification notification, Long id) {

        Notification existing = getById(id);
        existing.setName(notification.getName());
        existing.setDescription(notification.getDescription());
        existing.setChats(notification.getChats());
        existing.setSendDateTime(notification.getSendDateTime());
        existing.setFrequency(notification.getFrequency());
        existing.setNotificationType(notification.getNotificationType());
        notificationRepository.save(existing);
        return existing;
    }

    @Override
    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Notification> getDueNotifications(LocalDateTime currentTime) {
        return notificationRepository.findNotificationsForSending(currentTime);
    }

    @Override
    @Transactional
    public void updateNotificationSchedule(Notification notification, LocalDateTime newTime) {
        notification.setSendDateTime(newTime);
        notificationRepository.save(notification);
    }

    @Override
    @Transactional
    public void deleteOneTimeNotification(Notification notification) {
        notificationRepository.delete(notification);
    }

}
