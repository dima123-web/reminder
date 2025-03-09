package com.example.reminder.repository;

import com.example.reminder.models.notification.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    Chat findChatByName(String name);

}
