package kr.hs.dgsw.jetty.controller;

import jakarta.validation.Valid;
import kr.hs.dgsw.jetty.common.ApiResponse;
import kr.hs.dgsw.jetty.dto.request.ChatRequest;
import kr.hs.dgsw.jetty.dto.request.CreateConversationRequest;
import kr.hs.dgsw.jetty.dto.response.ChatResponse;
import kr.hs.dgsw.jetty.dto.response.ConversationResponse;
import kr.hs.dgsw.jetty.dto.response.CreateConversationResponse;
import kr.hs.dgsw.jetty.service.ChatService;
import kr.hs.dgsw.jetty.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chat/{conversationId}")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ChatResponse>>> getMessages(
            @PathVariable UUID conversationId
    ) {
        List<ChatResponse> response = chatService.getMessages(conversationId);
        return ResponseEntity.ok(
                ApiResponse.of(response));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ChatResponse>> sendMessage(
            @PathVariable UUID conversationId,
            @Valid @RequestBody ChatRequest request
    ) {
        ChatResponse response = chatService.sendMessage(conversationId, request.userText());
        return ResponseEntity.ok(
                ApiResponse.of(response));
    }
}
