package com.example.reminder.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    public void sendNotificationEvent(NotificationEvent event) {
        kafkaTemplate.send("notifications", event);
    }

}
