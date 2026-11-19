package kr.hs.dgsw.jetty.chat.dto.response;

import kr.hs.dgsw.jetty.chat.entity.Chat;

public record ChatResponse(
        String userText,
        String replyText
) {
    public static ChatResponse from(Chat entity) {
        return new ChatResponse(entity.getUserText(), entity.getReplyText());
    }
}
