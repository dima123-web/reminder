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
}
