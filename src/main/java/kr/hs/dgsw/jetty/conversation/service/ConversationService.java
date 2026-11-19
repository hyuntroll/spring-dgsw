package kr.hs.dgsw.jetty.conversation.service;

import kr.hs.dgsw.jetty.conversation.dto.response.ConversationResponse;
import kr.hs.dgsw.jetty.conversation.dto.response.CreateConversationResponse;

import java.util.List;
import java.util.UUID;

public interface ConversationService {
    CreateConversationResponse createConversation(Long personaId);

    List<ConversationResponse> getConversations();

    ConversationResponse getConversation(UUID conversationId);

    void deleteConversation(UUID conversationId);
}
