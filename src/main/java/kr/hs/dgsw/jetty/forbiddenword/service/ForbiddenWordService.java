package kr.hs.dgsw.jetty.forbiddenword.service;

import kr.hs.dgsw.jetty.common.ApiResponse;
import kr.hs.dgsw.jetty.common.exception.ApplicationException;
import kr.hs.dgsw.jetty.forbiddenword.entity.ForbiddenWord;
import kr.hs.dgsw.jetty.forbiddenword.exception.ForbiddenWordStatusCode;
import kr.hs.dgsw.jetty.forbiddenword.repository.ForbiddenWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ForbiddenWordService {
    private final ForbiddenWordRepository forbiddenWordRepository;

    public boolean containesForbiddenWords(String text) {
        if (text == null) {
            return false;
        }

        String target = normalize(text);

        for (ForbiddenWord forbiddenWord : forbiddenWordRepository.findAll()) {
            if (target.contains(normalize(forbiddenWord.getWord()))) {
                return true;
            }
        }
        return false;
    }

    public void validate(String text) {
        if (containesForbiddenWords(text)) {
            throw ApplicationException.of(ForbiddenWordStatusCode.FORBIDDEN_WORD_DETECTED);
        }
    }

    public void addWords(ForbiddenWord forbiddenWord) {
        forbiddenWordRepository.save(forbiddenWord);
    }

    public List<ForbiddenWord> getWords() {
        return forbiddenWordRepository.findAll();
    }

    public void deleteWords(Long id) {
        forbiddenWordRepository.deleteById(id);
    }

    private String normalize(String text) {
        return text.replace(" ", "").toLowerCase();
    }
}
