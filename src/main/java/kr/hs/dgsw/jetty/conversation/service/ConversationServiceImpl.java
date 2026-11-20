package kr.hs.dgsw.jetty.conversation.service;

import kr.hs.dgsw.jetty.chat.entity.Chat;
import kr.hs.dgsw.jetty.chat.repository.ChatRepository;
import kr.hs.dgsw.jetty.common.exception.ApplicationException;
import kr.hs.dgsw.jetty.conversation.dto.response.ConversationResponse;
import kr.hs.dgsw.jetty.conversation.dto.response.CreateConversationResponse;
import kr.hs.dgsw.jetty.conversation.entity.Conversation;
import kr.hs.dgsw.jetty.conversation.exception.ConversationStatusCode;
import kr.hs.dgsw.jetty.conversation.repository.ConversationRepository;
import kr.hs.dgsw.jetty.persona.entity.Persona;
import kr.hs.dgsw.jetty.persona.exception.PersonaStatusCode;
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
    private final ChatRepository chatRepository;

    @Override
    @Transactional
    public CreateConversationResponse createConversation(Long personaId) {
        Persona persona = personaRepository.findById(personaId)
                .orElseThrow(() -> new ApplicationException(PersonaStatusCode.PERSONA_NOT_FOUND));
        Conversation conversation = conversationRepository.save(Conversation.create(persona));

        chatRepository.save(
                Chat.greeting(conversation, persona.getGreeting())
        );
        return CreateConversationResponse.from(conversation);
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
                .orElseThrow(() -> new ApplicationException(ConversationStatusCode.CONVERSATION_NOT_FOUND));

        conversationRepository.delete(conversation);
    }
}
