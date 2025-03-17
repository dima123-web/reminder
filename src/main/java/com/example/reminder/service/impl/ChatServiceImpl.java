package com.example.reminder.service.impl;

import com.example.reminder.repository.ChatRepository;
import com.example.reminder.service.ChatService;
import com.example.reminder.web.dto.notification.ChatResponseDto;
import com.example.reminder.web.mappers.ChatMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    public List<ChatResponseDto> getAllChats() {
        log.info("List<ChatResponseDto>: {}", chatRepository.findAll().stream().map(ChatMapper::toDto).toList());
        return chatRepository.findAll().stream().map(ChatMapper::toDto).toList();
    }
}
