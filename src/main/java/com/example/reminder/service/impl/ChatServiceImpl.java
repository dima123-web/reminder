package com.example.reminder.service.impl;

import com.example.reminder.exception.ResourceNotFoundException;
import com.example.reminder.models.notification.Chat;
import com.example.reminder.repository.ChatRepository;
import com.example.reminder.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    public Chat getById(Long id) {
        return chatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chat not found"));
    }

    @Override
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
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
