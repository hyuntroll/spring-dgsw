package kr.hs.dgsw.jetty.persona.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.hs.dgsw.jetty.persona.entity.Personality;
import kr.hs.dgsw.jetty.persona.entity.SpeechStyle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaRequest {
    @NotBlank
    private String name;

    @NotNull
    private Personality personality;

    @NotNull
    private SpeechStyle speechStyle;

    @NotBlank
    private String greeting;
}
