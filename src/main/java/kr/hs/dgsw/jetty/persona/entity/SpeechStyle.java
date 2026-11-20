package kr.hs.dgsw.jetty.persona.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SpeechStyle {
    POLITE("존댓말"),
    CASUAL("반말");

    private final String description;
}
