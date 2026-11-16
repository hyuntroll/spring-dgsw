package kr.hs.dgsw.jetty.controller;

import jakarta.validation.Valid;
import kr.hs.dgsw.jetty.common.ApiResponse;
import kr.hs.dgsw.jetty.dto.ChatResponse;
import kr.hs.dgsw.jetty.dto.CreateConversationResponse;
import kr.hs.dgsw.jetty.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/conversations")
@RequiredArgsConstructor
public class ConversationController {
    private final ConversationService conversationService;

    @PostMapping
    public ResponseEntity<ApiResponse<CreateConversationResponse>> create(
            @Valid @RequestBody CreateConversationRequest request
    ) {
        CreateConversationResponse response = conversationService.createConversation(request.personaId());
        return ResponseEntity.status(201)
                .body(ApiResponse.of(response));
    }

    @PostMapping("/{conversationId}")
    public ResponseEntity<ApiResponse<ChatResponse>> chat(
            @PathVariable UUID conversationId,
            @Valid @RequestBody ChatRequest request
    ) {
        ChatResponse response = conversationService.sendMessage(conversationId, request.userText());
        return ResponseEntity.ok(
                ApiResponse.of(response));
    }
}
