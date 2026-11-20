package kr.hs.dgsw.jetty.conversation.dto.response;

import kr.hs.dgsw.jetty.conversation.entity.Conversation;
import kr.hs.dgsw.jetty.persona.entity.Persona;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ConversationResponse(
        UUID conversationId,
        Long personaId,
        String personaName,
        String personality,
        String speechStyle
) {
    public static ConversationResponse from(Conversation entity) {
        Persona persona = entity.getPersona();
        return new ConversationResponse(
                entity.getId(),
                persona.getId(),
                persona.getName(),
                persona.getPersonality().getDescription(),
                persona.getSpeechStyle().getDescription()
        );
    }
}
