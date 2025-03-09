package com.example.reminder.models.notification;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "notification")
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "notification_chats",
            joinColumns = @JoinColumn(name = "notification_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id")
    )
    private List<Chat> chats; // Список чатов, куда отправлять уведомление

    @Column(name = "send_date_time")
    private LocalDateTime sendDateTime; // Дата и время отправки

    @Enumerated(EnumType.STRING)
    @Column(name = "frequency")
    private Frequency frequency; // Периодичность (ежедневно, еженедельно и т.д.)

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type")
    private NotificationType notificationType; // Тип уведомления (дейлик, митинг, дедлайн и т.д.)

}
