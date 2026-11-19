package kr.hs.dgsw.jetty.service;

import kr.hs.dgsw.jetty.common.exception.ApplicationException;
import kr.hs.dgsw.jetty.common.exception.CommonStatusCode;
import kr.hs.dgsw.jetty.dto.response.ChatResponse;
import kr.hs.dgsw.jetty.dto.response.ConversationResponse;
import kr.hs.dgsw.jetty.dto.response.CreateConversationResponse;
import kr.hs.dgsw.jetty.entity.Chat;
import kr.hs.dgsw.jetty.entity.Conversation;
import kr.hs.dgsw.jetty.exception.ConversationStatusCode;
import kr.hs.dgsw.jetty.repository.ChatRepository;
import kr.hs.dgsw.jetty.repository.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;

    @Override
    @Transactional
    public CreateConversationResponse createConversation(Long personaId) {
        return CreateConversationResponse.from(
                conversationRepository.save(
                        Conversation.create(personaId)
                )
        );
    }

    @Override
    public List<ConversationResponse> getConversations() {
        return conversationRepository.findAll()
                .stream()
                .map(ConversationResponse::from)
                .toList();
    }

    @Override
    public ConversationResponse getConversation(UUID conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ApplicationException(ConversationStatusCode.CONVERSATION_NOT_FOUND));

        return ConversationResponse.from(conversation);
    }

    @Override
    @Transactional
    public void deleteConversation(UUID conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ApplicationException(CommonStatusCode.NOT_FOUND));

        conversationRepository.delete(conversation);
    }
}
