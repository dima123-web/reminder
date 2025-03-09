package com.example.reminder.models.notification;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "chat")
@Data
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "chat_id", unique = true)
    private String chatId;

}
