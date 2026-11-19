package kr.hs.dgsw.jetty.conversation.controller;

import jakarta.validation.Valid;
import kr.hs.dgsw.jetty.common.ApiResponse;
import kr.hs.dgsw.jetty.conversation.dto.request.CreateConversationRequest;
import kr.hs.dgsw.jetty.conversation.dto.response.ConversationResponse;
import kr.hs.dgsw.jetty.conversation.dto.response.CreateConversationResponse;
import kr.hs.dgsw.jetty.conversation.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conversations")
@RequiredArgsConstructor
public class ConversationController {
    private final ConversationService conversationService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ConversationResponse>>> findAll() {
        List<ConversationResponse> conversations = conversationService.getConversations();
        return ResponseEntity.ok(
                ApiResponse.of(conversations)
        );
    }

    @GetMapping("/{conversationId}")
    public ResponseEntity<ApiResponse<ConversationResponse>> findById(
            @PathVariable UUID conversationId
    ) {
        ConversationResponse conversation = conversationService.getConversation(conversationId);
        return ResponseEntity.ok(
                ApiResponse.of(conversation)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateConversationResponse>> create(
            @Valid @RequestBody CreateConversationRequest request
    ) {
        CreateConversationResponse response = conversationService.createConversation(request.personaId());
        return ResponseEntity.status(201)
                .body(ApiResponse.of(response));
    }

    @DeleteMapping("/{conversationId}")
    public ResponseEntity<ApiResponse<Void>> deleteById(
            @PathVariable UUID conversationId
    ) {
        conversationService.deleteConversation(conversationId);
        return ResponseEntity.ok(
                ApiResponse.ok()
        );
    }

}
