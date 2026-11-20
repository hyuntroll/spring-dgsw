package kr.hs.dgsw.jetty.persona.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Personality {
    WARM("다정하고 따뜻한"),
    PLAYFUL("장난기 있고 유쾌한"),
    COOL("차분하고 시크한");

    private final String description;
}
