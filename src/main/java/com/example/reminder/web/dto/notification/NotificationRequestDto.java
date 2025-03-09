package com.example.reminder.web.dto.notification;

import com.example.reminder.models.notification.Frequency;
import com.example.reminder.models.notification.NotificationType;
import com.example.reminder.web.dto.validation.OnCreate;
import com.example.reminder.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class NotificationRequestDto {
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

    @Length(
            max = 255,
            message = "Description length must be smaller than 255 symbols.",
            groups = {OnCreate.class, OnUpdate.class}
    )
    private String description;

    @NotNull(
            message = "Chats must be not null.",
            groups = {OnCreate.class, OnUpdate.class}
    )
    private List<Long> chatIds; // первичный ключ чатов

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.TIME
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm"
    )
    private LocalDateTime sendDateTime;

    private Frequency frequency;

    private NotificationType notificationType;

}
