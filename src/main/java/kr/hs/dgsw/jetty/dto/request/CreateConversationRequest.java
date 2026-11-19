package kr.hs.dgsw.jetty.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateConversationRequest(
        @NotNull
        Long personaId
) {}
