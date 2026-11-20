package kr.hs.dgsw.jetty.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonStatusCode implements StatusCode {
    BAD_REQUEST("이름은 최대 10자, 인사말은 최대 200자까지 입력해 주세요.", 400),
    NOT_FOUND("404 not found", 404),
    INTERNAL_SERVER_ERROR("internal server error", 500);


    private final String message;
    private final int status;
}
