package kr.hs.dgsw.jetty.service;

import kr.hs.dgsw.jetty.common.exception.ApplicationException;
import kr.hs.dgsw.jetty.common.exception.CommonStatusCode;
import kr.hs.dgsw.jetty.dto.response.ChatResponse;
import kr.hs.dgsw.jetty.entity.Chat;
import kr.hs.dgsw.jetty.entity.Conversation;
import kr.hs.dgsw.jetty.exception.ChatStatusCode;
import kr.hs.dgsw.jetty.exception.ConversationStatusCode;
import kr.hs.dgsw.jetty.repository.ChatRepository;
import kr.hs.dgsw.jetty.repository.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
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
    private final ConversationRepository conversationRepository;
    private final ChatRepository chatRepository;
    private final ChatClient.Builder chatClientBuilder;

    private static final String systemPrompt = """
                        너는 사용자가 만든 캐릭터로 대화하는 AI다
                        
                        - 한국어로 자연스럽게 대답하고, 캐릭터의 성격과 말투를 유지해.
                        - 사용자의 최 메시지와 앞선 대화 맥락을 참고해서 답해.
                        - 질문에는 먼저 대답하고, 필요한 경우 공감이나 질문을 덧붙여.
                        - 기본적으로 1~3 문장으로 답해.
                        - 이전에 무슨 말을 했는 물으면 제공된 대화 내역에서 확인해 답해
                        
                        """;

    @Override
    @Transactional
    public ChatResponse sendMessage(UUID conversationId, String userText) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ApplicationException(ConversationStatusCode.CONVERSATION_NOT_FOUND));

        List<Chat> recentChats = chatRepository.findTop8ByConversationIdOrderByCreatedAtDescIdDesc(conversationId);

        List<Message> messages = new ArrayList<>();

        for (Chat chat : recentChats) {
            messages.add(new UserMessage(chat.getUserText()));
            messages.add(new AssistantMessage(chat.getReplyText()));
        }

        messages.add(new UserMessage(userText));

        String replyText = chatClientBuilder.build()
                .prompt()
                .system(systemPrompt)
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
}
