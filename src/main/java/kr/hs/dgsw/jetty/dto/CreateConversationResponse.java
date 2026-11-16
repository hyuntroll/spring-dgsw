package kr.hs.dgsw.jetty.dto;

import kr.hs.dgsw.jetty.entity.Conversation;

import java.util.UUID;

public record CreateConversationResponse(
        UUID conversationId
) {
    public static CreateConversationResponse from(Conversation entity) {
        return new CreateConversationResponse(entity.getId());
    }
}
