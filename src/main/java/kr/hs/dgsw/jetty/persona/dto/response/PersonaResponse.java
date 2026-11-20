package kr.hs.dgsw.jetty.persona.dto.response;

import kr.hs.dgsw.jetty.persona.entity.Persona;
import kr.hs.dgsw.jetty.persona.entity.Personality;
import kr.hs.dgsw.jetty.persona.entity.SpeechStyle;
import lombok.Builder;
import lombok.Getter;

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
