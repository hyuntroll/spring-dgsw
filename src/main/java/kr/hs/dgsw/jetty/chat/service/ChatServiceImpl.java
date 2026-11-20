package kr.hs.dgsw.jetty.chat.service;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingType;
import kr.hs.dgsw.jetty.common.exception.ApplicationException;
import kr.hs.dgsw.jetty.common.exception.CommonStatusCode;
import kr.hs.dgsw.jetty.chat.dto.response.ChatResponse;
import kr.hs.dgsw.jetty.chat.entity.Chat;
import kr.hs.dgsw.jetty.conversation.entity.Conversation;
import kr.hs.dgsw.jetty.chat.exception.ChatStatusCode;
import kr.hs.dgsw.jetty.conversation.exception.ConversationStatusCode;
import kr.hs.dgsw.jetty.forbiddenword.service.ForbiddenWordService;
import kr.hs.dgsw.jetty.chat.repository.ChatRepository;
import kr.hs.dgsw.jetty.conversation.repository.ConversationRepository;
import kr.hs.dgsw.jetty.persona.entity.Persona;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatServiceImpl implements ChatService {
    private final ForbiddenWordService forbiddenWordService;

    private final ConversationRepository conversationRepository;
    private final ChatRepository chatRepository;
    private final ChatClient.Builder chatClientBuilder;


    private static final String systemPromptTemplate = """
                        너는 사용자가 만든 캐릭터로 대화하는 AI다
                        
                        캐릭터 이름: %s
                        성격: %s
                        말투: %s
                        
                        - 한국어로 자연스럽게 대답하고, 캐릭터의 성격과 말투를 유지해.
                        - 사용자의 최 메시지와 앞선 대화 맥락을 참고해서 답해.
                        - 질문에는 먼저 대답하고, 필요한 경우 공감이나 질문을 덧붙여.
                        - 기본적으로 1~3 문장으로 답해.
                        - 이전에 무슨 말을 했는 물으면 제공된 대화 내역에서 확인해 답해
                        
                        """;
    private static final Encoding ENCODING = Encodings.newDefaultEncodingRegistry()
            .getEncoding(EncodingType.O200K_BASE);

    @Value("${jetty.chat.history-token-budget}")
    private int historyTokenBudget;

    @Override
    @Transactional
    public ChatResponse sendMessage(UUID conversationId, String userText) {
        forbiddenWordService.validate(userText);

        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ApplicationException(ConversationStatusCode.CONVERSATION_NOT_FOUND));

        List<Chat> recentChats = chatRepository.findTop8ByConversationIdOrderByCreatedAtDescIdDesc(conversationId);

        List<Message> messages = new ArrayList<>();

        for (Chat chat : selectHistory(recentChats)) {
            messages.add(new UserMessage(chat.getUserText()));
            messages.add(new AssistantMessage(chat.getReplyText()));
        }

        messages.add(new UserMessage(userText));

        String replyText = chatClientBuilder.build()
                .prompt()
                .system(generatePrompt(conversation.getPersona()))
                .messages(messages)
                .call()
                .content();

        if (!StringUtils.hasText(replyText)) {
            throw new ApplicationException(ChatStatusCode.EMPTY_AI_RESPONSE);
        }

        Chat saved = chatRepository.save(Chat.create(conversation, userText, replyText));

        return ChatResponse.from(saved);
    }

    @Override
    public List<ChatResponse> getMessages(UUID conversationId) {
        conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ApplicationException(CommonStatusCode.NOT_FOUND));

        return chatRepository.findByConversationIdOrderByCreatedAtDescIdDesc(conversationId)
                .stream()
                .map(ChatResponse::from)
                .toList();
    }

    private List<Chat> selectHistory(List<Chat> newestFirst) {
        List<Chat> selected = new ArrayList<>();
        int remainingTokens = historyTokenBudget;

        for (Chat chat : newestFirst) {
            int turnTokens = ENCODING.countTokensOrdinary(chat.getUserText())
                    + ENCODING.countTokensOrdinary(chat.getReplyText());

            if (turnTokens > remainingTokens) {
                break;
            }

            selected.add(chat);
            remainingTokens -= turnTokens;
        }

        return selected.reversed();
    }

    private String generatePrompt(Persona persona) {
        return systemPromptTemplate
                .formatted(
                        persona.getName(),
                        persona.getPersonality().getDescription(),
                        persona.getSpeechStyle().getDescription()
                );
    }
}
