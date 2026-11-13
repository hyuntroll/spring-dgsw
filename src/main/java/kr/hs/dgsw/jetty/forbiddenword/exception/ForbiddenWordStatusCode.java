package kr.hs.dgsw.jetty.forbiddenword.exception;

import kr.hs.dgsw.jetty.common.exception.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ForbiddenWordStatusCode implements StatusCode {
    FORBIDDEN_WORD_DETECTED("금칙어가 포함되어 있습니다.", 400);

    private final String message;
    private final int status;
}
