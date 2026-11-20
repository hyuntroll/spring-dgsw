package kr.hs.dgsw.jetty.conversation.dto.response;

import kr.hs.dgsw.jetty.conversation.entity.Conversation;
import lombok.Builder;

import java.util.UUID;


@Builder
public record CreateConversationResponse(
        UUID conversationId
) {
    public static CreateConversationResponse from(Conversation entity) {
        return new CreateConversationResponse(entity.getId());
    }
}
