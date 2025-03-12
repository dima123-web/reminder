package com.example.reminder.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    public void sendNotificationEvent(NotificationEvent event) throws ExecutionException, InterruptedException {
        kafkaTemplate.send("telegram-notifications-topic", event).get();
    }

}
