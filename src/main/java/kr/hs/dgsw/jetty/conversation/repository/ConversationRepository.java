package kr.hs.dgsw.jetty.conversation.repository;

import kr.hs.dgsw.jetty.conversation.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConversationRepository extends JpaRepository<Conversation, UUID> {
}
