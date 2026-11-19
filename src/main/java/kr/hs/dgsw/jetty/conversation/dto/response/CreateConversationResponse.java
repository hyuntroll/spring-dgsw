package kr.hs.dgsw.jetty.conversation.dto.response;

import kr.hs.dgsw.jetty.conversation.entity.Conversation;

import java.util.UUID;

public record CreateConversationResponse(
        UUID conversationId
) {
    public static CreateConversationResponse from(Conversation entity) {
        return new CreateConversationResponse(entity.getId());
    }
}
