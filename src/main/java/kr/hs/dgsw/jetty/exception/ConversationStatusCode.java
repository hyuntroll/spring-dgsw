package kr.hs.dgsw.jetty.exception;

import kr.hs.dgsw.jetty.common.exception.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConversationStatusCode implements StatusCode {
    CONVERSATION_NOT_FOUND("대화를 찾지 못했습니다.", 404),;


    private final String message;
    private final int status;
}
