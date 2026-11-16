package kr.hs.dgsw.jetty.controller;

import jakarta.validation.constraints.NotNull;

public record CreateConversationRequest(
        @NotNull
        Long personaId
) {}
