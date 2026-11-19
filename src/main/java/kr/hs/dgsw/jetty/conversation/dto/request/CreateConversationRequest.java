package kr.hs.dgsw.jetty.conversation.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateConversationRequest(
        @NotNull
        Long personaId
) {}
