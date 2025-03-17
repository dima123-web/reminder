package com.example.reminder.service.impl;

import com.example.reminder.exception.ResourceNotFoundException;
import com.example.reminder.models.notification.Chat;
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
    public Chat getById(Long id) {
        return chatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chat not found"));
    }

    @Override
    public List<ChatResponseDto> getAllChats() {
        log.info("List<ChatResponseDto>: {}", chatRepository.findAll().stream().map(ChatMapper::toDto).toList());
        return chatRepository.findAll().stream().map(ChatMapper::toDto).toList();
    }

    @Override
    public Chat create(Chat chat) {
        chatRepository.save(chat);
        return chat;
    }

    @Override
    public Chat update(Chat chat, Long id) {
        Chat existing = getById(id);
        existing.setName(chat.getName());
        existing.setChatId(chat.getChatId());
        chatRepository.save(existing);
        return existing;
    }

    @Override
    public void delete(Long id) {
        chatRepository.deleteById(id);
    }
}
