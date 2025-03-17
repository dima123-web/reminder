package com.example.reminder.web.dto.notification;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatResponseDto {
    private Long id;
    private String name;
}
