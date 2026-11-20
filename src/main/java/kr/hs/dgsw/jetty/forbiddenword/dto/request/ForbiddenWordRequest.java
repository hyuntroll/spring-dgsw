package kr.hs.dgsw.jetty.forbiddenword.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ForbiddenWordRequest(
        @NotBlank(message = "금칙어를 입력해주세요.")
        String word
) {}
