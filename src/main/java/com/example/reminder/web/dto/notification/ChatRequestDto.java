package com.example.reminder.web.dto.notification;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChatRequestDto {

    @NotNull(
            message = "Name must be not null."
    )
    @Length(
            max = 255,
            message = "Name length must be smaller than 255 symbols."
    )
    private String name;

    @NotNull(
            message = "Chat id must be not null."
    )
    private String chatId;

}
