package kr.hs.dgsw.jetty.conversation.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateConversationRequest(
        @NotNull(message = "페르소나 ID는 필수입니다.")
        Long personaId
) {}
