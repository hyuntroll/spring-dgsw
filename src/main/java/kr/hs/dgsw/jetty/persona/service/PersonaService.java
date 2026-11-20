package kr.hs.dgsw.jetty.persona.service;

import kr.hs.dgsw.jetty.persona.dto.request.PersonaRequest;
import kr.hs.dgsw.jetty.persona.dto.response.PersonaResponse;

import java.util.List;

public interface PersonaService {
    
    PersonaResponse createPersona(PersonaRequest request);

    List<PersonaResponse> getPersonas();

    PersonaResponse getPersona(Long personaId);

    PersonaResponse updatePersona(Long personaId, PersonaRequest request);

    void deletePersona(Long personaId);
}
