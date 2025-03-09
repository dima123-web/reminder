package com.example.reminder.scheduler;

import com.example.reminder.models.notification.Chat;
import com.example.reminder.models.notification.Frequency;
import com.example.reminder.models.notification.Notification;
import com.example.reminder.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationScheduler {
    private final NotificationService notificationService;
    private final KafkaProducerService kafkaProducerService;

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void checkAndSendNotifications() {
        LocalDateTime now = LocalDateTime.now();
        List<Notification> notifications = notificationService.getDueNotifications(now);

        notifications.forEach(notification -> {
            kafkaProducerService.sendNotificationEvent(convertToEvent(notification));

            if (notification.getFrequency() != Frequency.ONCE) {
                LocalDateTime nextTime = calculateNextTime(notification.getSendDateTime(), notification.getFrequency());
                notificationService.updateNotificationSchedule(notification, nextTime);
            } else {
                notificationService.deleteOneTimeNotification(notification);
            }
        });
    }

    private NotificationEvent convertToEvent(Notification notification) {
        return NotificationEvent.builder()
                .notificationId(notification.getId())
                .chats(notification.getChats().stream().map(Chat::getChatId).toList())
                .type(notification.getNotificationType())
                .message(buildMessage(notification))
                .build();
    }

    private String buildMessage(Notification notification) {
        return String.format(
                "%s\nОписание: %s\nДата: %s",
                notification.getName(),
                notification.getDescription(),
                notification.getSendDateTime().format(DateTimeFormatter.ISO_DATE_TIME)
        );
    }

    private LocalDateTime calculateNextTime(LocalDateTime currentTime, Frequency frequency) {
        return switch (frequency) {
            case EVERY_DAY -> currentTime.plusDays(1);
            case WEEKLY -> currentTime.plusWeeks(1);
            case MONTHLY -> currentTime.plusMonths(1);
            default -> throw new IllegalArgumentException("Unsupported frequency: " + frequency);
        };
    }

}
