package com.example.reminder.service;

import com.example.reminder.models.notification.Chat;
import com.example.reminder.web.dto.notification.ChatResponseDto;

import java.util.List;

public interface ChatService {

    Chat getById(Long id);

    List<ChatResponseDto> getAllChats();

    Chat create(Chat chat);

    Chat update(Chat chat, Long id);

    void delete(Long id);

}
