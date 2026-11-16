package kr.hs.dgsw.jetty.controller;

import jakarta.validation.constraints.NotBlank;

public record ChatRequest(
        @NotBlank
        String userText
) {}
