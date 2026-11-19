package kr.hs.dgsw.jetty.persona.dto.response;

import jakarta.validation.constraints.NotNull;
import kr.hs.dgsw.jetty.persona.entity.Persona;
import kr.hs.dgsw.jetty.persona.entity.Personality;
import kr.hs.dgsw.jetty.persona.entity.SpeechStyle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class PersonaResponse {
    private Long personaId;
    private String name;
    private Personality personality;
    private SpeechStyle speechStyle;
    private String greeting;

    public static PersonaResponse from(Persona persona) {
        return PersonaResponse.builder()
                .personaId(persona.getPersonaId())
                .name(persona.getName())
                .personality(persona.getPersonality())
                .speechStyle(persona.getSpeechStyle())
                .greeting(persona.getGreeting())
                .build();
    }
}
