package kr.hs.dgsw.jetty.service;

import kr.hs.dgsw.jetty.dto.response.ChatResponse;

import java.util.List;
import java.util.UUID;

public interface ChatService {
    ChatResponse sendMessage(UUID conversationId, String userText);

    List<ChatResponse> getMessages(UUID conversationId);
}
