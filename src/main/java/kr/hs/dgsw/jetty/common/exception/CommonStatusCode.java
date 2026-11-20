package kr.hs.dgsw.jetty.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonStatusCode implements StatusCode {
    BAD_REQUEST("400 bad request", 400),
    NOT_FOUND("404 not found", 404),
    INTERNAL_SERVER_ERROR("internal server error", 500);


    private final String message;
    private final int status;
}
