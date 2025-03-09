package com.example.reminder.web.mappers;


import com.example.reminder.exception.ResourceNotFoundException;
import com.example.reminder.models.notification.Chat;
import com.example.reminder.models.notification.Notification;
import com.example.reminder.repository.ChatRepository;
import com.example.reminder.web.dto.notification.NotificationRequestDto;
import com.example.reminder.web.dto.notification.NotificationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public  abstract class NotificationMapper {

    @Autowired
    private ChatRepository chatRepository;

    @Mapping(source = "chats", target = "chatIds", qualifiedByName = "mapChatIds")
    public abstract NotificationResponseDto toDto(Notification notification);

    @Mapping(source = "chatIds", target = "chats", qualifiedByName = "mapChatIdsToChats")
    public abstract Notification toEntity(NotificationRequestDto dto);

    @Named("mapChatIds")
    protected List<Long> mapChatIds(List<Chat> chats) {
        if (chats == null) {
            return Collections.emptyList();
        }
        return chats.stream()
                .map(Chat::getId)
                .collect(Collectors.toList());
    }

    // Преобразование списка Long в список Chat с поиском в базе данных
    @Named("mapChatIdsToChats")
    protected List<Chat> mapChatIdsToChats(List<Long> chatIds) {
        if (chatIds == null) {
            return Collections.emptyList();
        }
        return chatIds.stream()
                .map(chatId -> chatRepository.findById(chatId)
                        .orElseThrow(() -> new ResourceNotFoundException("Chat not found with id: " + chatId)))
                .collect(Collectors.toList());
    }
}

