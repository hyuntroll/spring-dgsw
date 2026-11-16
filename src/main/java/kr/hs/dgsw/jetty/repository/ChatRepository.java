package kr.hs.dgsw.jetty.repository;

import kr.hs.dgsw.jetty.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findTop8ByConversationIdOrderByCreatedAtDescIdDesc(UUID conversationId);
}
