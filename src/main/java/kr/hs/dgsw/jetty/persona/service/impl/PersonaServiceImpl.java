package kr.hs.dgsw.jetty.persona.service.impl;

import kr.hs.dgsw.jetty.common.exception.ApplicationException;
import kr.hs.dgsw.jetty.common.exception.CommonStatusCode;
import kr.hs.dgsw.jetty.persona.dto.request.PersonaRequest;
import kr.hs.dgsw.jetty.persona.dto.response.PersonaResponse;
import kr.hs.dgsw.jetty.persona.entity.Persona;
import kr.hs.dgsw.jetty.persona.repository.PersonaRepository;
import kr.hs.dgsw.jetty.persona.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository repository;

    @Override
    @Transactional
    public PersonaResponse createPersona(PersonaRequest request) {
        Persona persona = Persona.builder()
                .name(request.getName())
                .personality(request.getPersonality())
                .speechStyle(request.getSpeechStyle())
                .greeting(request.getGreeting())
                .build();

        Persona save = repository.save(persona);
        return PersonaResponse.from(save);

    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonaResponse> getPersonas() {
        return repository.findAllByOrderByPersonaIdAsc().stream()
                .map(PersonaResponse::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PersonaResponse getPersona(Long personaId) {
        return PersonaResponse.from(findPersona(personaId));
    }

    @Override
    @Transactional
    public PersonaResponse updatePersona(Long personaId, PersonaRequest request) {
        Persona persona = findPersona(personaId);
        validateLengths(request);

        persona.setName(request.getName());
        persona.setPersonality(request.getPersonality());
        persona.setSpeechStyle(request.getSpeechStyle());
        persona.setGreeting(request.getGreeting());

        return PersonaResponse.from(persona);
    }

    @Override
    @Transactional
    public void deletePersona(Long personaId) {
        repository.delete(findPersona(personaId));
    }

    private Persona findPersona(Long personaId) {
        return repository.findById(personaId)
                .orElseThrow(() -> ApplicationException.of(CommonStatusCode.NOT_FOUND));
    }

    private void validateLengths(PersonaRequest request) {
        if (request.getName().length() > 10 || request.getGreeting().length() > 200) {
            throw ApplicationException.of(CommonStatusCode.BAD_REQUEST);
        }
    }
}
