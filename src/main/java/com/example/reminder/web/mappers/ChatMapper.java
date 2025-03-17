package com.example.reminder.web.mappers;

import com.example.reminder.models.notification.Chat;
import com.example.reminder.web.dto.notification.ChatResponseDto;

public class ChatMapper {

    public static ChatResponseDto toDto(Chat chat){
        return ChatResponseDto.builder().id(chat.getId())
                .name(chat.getName()).build();
    }

}
