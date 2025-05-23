package com.example.reminder.scheduler;

import com.example.reminder.models.notification.Chat;
import com.example.reminder.models.notification.Frequency;
import com.example.reminder.models.notification.Notification;
import com.example.reminder.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationScheduler {
    private final NotificationService notificationService;
    private final KafkaProducerService kafkaProducerService;

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void checkAndSendNotifications() {
        LocalDateTime now = LocalDateTime.now();
        log.info(now.toString());
        List<Notification> notifications = notificationService.getDueNotifications(now);
        log.info("notifications :{}",notifications);

        notifications.forEach(notification -> {
            log.info("Start send");
            try {
                String randomId = UUID.randomUUID().toString();
                kafkaProducerService.sendNotificationEvent(convertToEvent(notification, randomId));
            } catch (InterruptedException e) {
                log.error("Ошибка при отправке в kafka, поток прерван во премя ожидания:" + e.getMessage());
                return;
            } catch (ExecutionException e) {
                log.error("Ошибка при отправке в kafka:" + e.getMessage());
                return;
            }

            if (notification.getFrequency() != Frequency.ONCE) {
                LocalDateTime nextTime = calculateNextTime(notification.getSendDateTime(), notification.getFrequency());
                notificationService.updateNotificationSchedule(notification, nextTime);
            } else {
                notificationService.deleteOneTimeNotification(notification);
            }
        });
    }

    private NotificationEvent convertToEvent(Notification notification, String randomId) {
        return NotificationEvent.builder()
                .messageId(randomId + "_" + notification.getId())
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
            case WEEKDAY ->currentTime.getDayOfWeek() != DayOfWeek.FRIDAY ? currentTime.plusDays(1): currentTime.plusDays(3);
            default -> throw new IllegalArgumentException("Unsupported frequency: " + frequency);
        };
    }

}
