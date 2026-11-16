package kr.hs.dgsw.jetty.dto;

import kr.hs.dgsw.jetty.entity.Conversation;

import java.util.UUID;

public record ConversationResponse(
        UUID conversationId,
        Long personaId
) {
    public static ConversationResponse from(Conversation entity) {
        return new ConversationResponse(entity.getId(), entity.getPersonaId());
    }
}
