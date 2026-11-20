package kr.hs.dgsw.jetty.forbiddenword.dto.response;

import kr.hs.dgsw.jetty.forbiddenword.entity.ForbiddenWord;

public record ForbiddenWordResponse(
        Long id,
        String word
) {
    public static ForbiddenWordResponse from(ForbiddenWord entity) {
        return new ForbiddenWordResponse(
                entity.getId(),
                entity.getWord()
        );
    }
}
