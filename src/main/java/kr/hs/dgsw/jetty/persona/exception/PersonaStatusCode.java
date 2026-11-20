package kr.hs.dgsw.jetty.persona.exception;

import kr.hs.dgsw.jetty.common.exception.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PersonaStatusCode implements StatusCode {
    PERSONA_NOT_FOUND("페르소나를 찾지 못했습니다.", 404),
    PERSONA_IN_USE("대화방에서 사용중인 페르소나가 있어 삭제할 수 없습니다.", 409);

    private final String message;
    private final int status;
}
