package kr.hs.dgsw.jetty.service;

import kr.hs.dgsw.jetty.dto.ChatResponse;
import kr.hs.dgsw.jetty.dto.ConversationResponse;
import kr.hs.dgsw.jetty.dto.CreateConversationResponse;

import java.util.List;
import java.util.UUID;

public interface ConversationService {
    CreateConversationResponse createConversation(Long personaId);

    List<ConversationResponse> getConversations();

    ConversationResponse getConversation(UUID conversationId);

    ChatResponse sendMessage(UUID conversationId, String userText);

    List<ChatResponse> getMessages(UUID conversationId);

    void deleteConversation(UUID conversationId);
}
