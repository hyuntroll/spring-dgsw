package kr.hs.dgsw.jetty.persona.controller;

import jakarta.validation.Valid;
import kr.hs.dgsw.jetty.common.ApiResponse;
import kr.hs.dgsw.jetty.persona.dto.request.PersonaRequest;
import kr.hs.dgsw.jetty.persona.dto.response.PersonaResponse;
import kr.hs.dgsw.jetty.persona.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/personas")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonaResponse createPersona(@Valid @RequestBody PersonaRequest request) {
        return personaService.createPersona(request);
    }

    @GetMapping
    public List<PersonaResponse> getPersonas() {
        return personaService.getPersonas();
    }

    @GetMapping("/{personaId}")
    public PersonaResponse getPersona(@PathVariable Long personaId) {
        return personaService.getPersona(personaId);
    }

    @PutMapping("/{personaId}")
    public PersonaResponse updatePersona(
            @PathVariable Long personaId,
            @Valid @RequestBody PersonaRequest request
    ) {
        return personaService.updatePersona(personaId, request);
    }

    @DeleteMapping("/{personaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersona(@PathVariable Long personaId) {
        personaService.deletePersona(personaId);
    }
}