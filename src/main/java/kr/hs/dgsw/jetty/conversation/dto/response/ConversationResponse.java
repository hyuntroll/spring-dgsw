package kr.hs.dgsw.jetty.conversation.dto.response;

import kr.hs.dgsw.jetty.conversation.entity.Conversation;

import java.util.UUID;

public record ConversationResponse(
        UUID conversationId,
        Long personaId
) {
    public static ConversationResponse from(Conversation entity) {
        return new ConversationResponse(entity.getId(), entity.getPersonaId());
    }
}
