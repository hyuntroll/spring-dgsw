package kr.hs.dgsw.jetty.repository;

import kr.hs.dgsw.jetty.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findTop8ByConversationIdOrderByCreatedAtDescIdDesc(UUID conversationId);

    @Query("""
    SELECT c
    FROM Chat c
    WHERE c.conversation.id = :conversationId
    ORDER BY c.createdAt DESC, c.id DESC
    """)
    List<Chat> findByConversationIdOrderByCreatedAtDescIdDesc(UUID conversationId);
}
