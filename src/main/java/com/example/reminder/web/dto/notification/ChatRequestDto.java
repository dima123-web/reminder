package com.example.reminder.web.dto.notification;

import com.example.reminder.web.dto.validation.OnCreate;
import com.example.reminder.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChatRequestDto {

    @NotNull(
            message = "Name must be not null.",
            groups = {OnCreate.class, OnUpdate.class}
    )
    @Length(
            max = 255,
            message = "Name length must be smaller than 255 symbols.",
            groups = {OnCreate.class, OnUpdate.class}
    )
    private String name;

    @NotNull(
            message = "Chat id must be not null.",
            groups = {OnCreate.class, OnUpdate.class}
    )
    private String chatId;

}
