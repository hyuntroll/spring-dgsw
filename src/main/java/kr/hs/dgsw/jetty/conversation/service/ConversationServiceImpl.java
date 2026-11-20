package kr.hs.dgsw.jetty.conversation.service;

import kr.hs.dgsw.jetty.common.exception.ApplicationException;
import kr.hs.dgsw.jetty.common.exception.CommonStatusCode;
import kr.hs.dgsw.jetty.conversation.dto.response.ConversationResponse;
import kr.hs.dgsw.jetty.conversation.dto.response.CreateConversationResponse;
import kr.hs.dgsw.jetty.conversation.entity.Conversation;
import kr.hs.dgsw.jetty.conversation.exception.ConversationStatusCode;
import kr.hs.dgsw.jetty.conversation.repository.ConversationRepository;
import kr.hs.dgsw.jetty.persona.entity.Persona;
import kr.hs.dgsw.jetty.persona.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final PersonaRepository personaRepository;

    @Override
    @Transactional
    public CreateConversationResponse createConversation(Long personaId) {
        Persona persona = personaRepository.findById(personaId)
                .orElseThrow(() -> new ApplicationException(CommonStatusCode.NOT_FOUND));

        return CreateConversationResponse.from(
                conversationRepository.save(
                        Conversation.create(persona)
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
