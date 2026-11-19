package kr.hs.dgsw.jetty.exception;

import kr.hs.dgsw.jetty.common.exception.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatStatusCode implements StatusCode {
    EMPTY_AI_RESPONSE("AI 답장이 비어 있습니다.", 502);


    private final String message;
    private final int status;
}
