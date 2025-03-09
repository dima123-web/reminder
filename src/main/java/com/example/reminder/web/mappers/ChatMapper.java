package com.example.reminder.web.mappers;

import com.example.reminder.models.notification.Chat;
import com.example.reminder.web.dto.notification.ChatRequestDto;
import com.example.reminder.web.dto.notification.ChatResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    ChatResponseDto toDto(Chat chat);

    Chat toEntity(ChatRequestDto dto);

}
