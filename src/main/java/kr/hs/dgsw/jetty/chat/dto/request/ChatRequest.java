package kr.hs.dgsw.jetty.chat.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ChatRequest(
        @NotBlank(message = "질문은 필수입니다.")
        String userText
) {}
