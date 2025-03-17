package com.example.reminder.service;

import com.example.reminder.web.dto.notification.ChatResponseDto;

import java.util.List;

public interface ChatService {

    List<ChatResponseDto> getAllChats();
}
