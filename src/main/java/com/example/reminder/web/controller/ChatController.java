package com.example.reminder.web.controller;

import com.example.reminder.service.ChatService;
import com.example.reminder.web.dto.notification.ChatResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/chats")
@Validated
public class ChatController {

    private final ChatService chatService;

    @GetMapping()
    public ResponseEntity<List<ChatResponseDto>> getChats() {
        return ResponseEntity.ok(chatService.getAllChats());
    }

//    @GetMapping("/{id}")
//    public ChatResponseDto getById(@PathVariable Long id) {
//        Chat chat = chatService.getById(id);
//        return chatMapper.toDto(chat);
//
//    }
//
//    @PostMapping
//    public ChatResponseDto create(@Validated(OnCreate.class) @RequestBody ChatRequestDto dto) {
//        Chat chat = chatMapper.toEntity(dto);
//        Chat createdChat = chatService.create(chat);
//        return chatMapper.toDto(createdChat);
//    }
//
//    @PutMapping
//    public ChatResponseDto update(Long id, @Validated(OnUpdate.class) @RequestBody ChatRequestDto dto) {
//        Chat chat = chatMapper.toEntity(dto);
//        Chat updatedChat = chatService.update(chat, id);
//        return chatMapper.toDto(updatedChat);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteById(@PathVariable final Long id) {
//        chatService.delete(id);
//    }

}
