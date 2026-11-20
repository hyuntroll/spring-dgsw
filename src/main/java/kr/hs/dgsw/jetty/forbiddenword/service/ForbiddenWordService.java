package kr.hs.dgsw.jetty.forbiddenword.service;

import kr.hs.dgsw.jetty.forbiddenword.dto.request.ForbiddenWordRequest;
import kr.hs.dgsw.jetty.forbiddenword.dto.response.ForbiddenWordResponse;

import java.util.List;

public interface ForbiddenWordService {
    boolean containesForbiddenWords(String text);

    void validate(String text);

    void addWords(ForbiddenWordRequest request);

    List<ForbiddenWordResponse> getWords();

    void deleteWords(Long id);
}
