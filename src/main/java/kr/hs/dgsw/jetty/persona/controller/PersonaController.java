package kr.hs.dgsw.jetty.persona.controller;

import jakarta.validation.Valid;
import kr.hs.dgsw.jetty.common.ApiResponse;
import kr.hs.dgsw.jetty.persona.dto.request.PersonaRequest;
import kr.hs.dgsw.jetty.persona.dto.response.PersonaResponse;
import kr.hs.dgsw.jetty.persona.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PersonaResponse> createPersona(
            @Valid @RequestBody PersonaRequest request
    ) {
        return ApiResponse.of(personaService.createPersona(request));
    }

    @GetMapping
    public ApiResponse<List<PersonaResponse>> getPersonas() {
        return ApiResponse.of(personaService.getPersonas());
    }

    @GetMapping("/{personaId}")
    public ApiResponse<PersonaResponse> getPersona(
            @PathVariable Long personaId
    ) {
        return ApiResponse.of(personaService.getPersona(personaId));
    }

    @PutMapping("/{personaId}")
    public ApiResponse<PersonaResponse> updatePersona(
            @PathVariable Long personaId,
            @Valid @RequestBody PersonaRequest request
    ) {
        return ApiResponse.of(personaService.updatePersona(personaId, request));
    }

    @DeleteMapping("/{personaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersona(@PathVariable Long personaId) {
        personaService.deletePersona(personaId);
    }
}