package com.example.reminder.comsumer;

import com.example.reminder.models.notification.Chat;
import com.example.reminder.repository.ChatRepository;
import com.example.reminder.scheduler.ChatRegisterEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@KafkaListener(topics = "${kafka.topic.notification}")
public class KafkaConsumer {
    private final ChatRepository chatRepository;

    @KafkaHandler(isDefault = true)
    public void handle(ConsumerRecord<String, ChatRegisterEvent> record) {
        ChatRegisterEvent message = record.value();
        if (message == null) {
            Header errorHeader = record.headers().lastHeader("springDeserializerExceptionValue");
            if (errorHeader != null) {
                String errorMessage = new String(errorHeader.value());
                log.error("Ошибка десериализации сообщения: {}", errorMessage);
            } else {
                log.warn("Получено null-сообщение без дополнительной информации об ошибке десериализации");
            }
            return;
        }

        if (message.getChatId() == null || message.getChatId().isBlank()) {
            log.warn("Получено уведомление без id");
            return;
        }

        log.info("Получено уведомление: {}", message);
        Chat newChat = new Chat();
        newChat.setChatId(message.getChatId());
        newChat.setName(message.getTitle());
        if (chatRepository.findChatByChatId(message.getChatId()) == null) {
            chatRepository.save(newChat);
        }
    }
}
