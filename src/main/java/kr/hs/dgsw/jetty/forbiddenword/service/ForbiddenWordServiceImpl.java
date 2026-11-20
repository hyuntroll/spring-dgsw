package kr.hs.dgsw.jetty.forbiddenword.service;

import kr.hs.dgsw.jetty.common.exception.ApplicationException;
import kr.hs.dgsw.jetty.forbiddenword.dto.request.ForbiddenWordRequest;
import kr.hs.dgsw.jetty.forbiddenword.dto.response.ForbiddenWordResponse;
import kr.hs.dgsw.jetty.forbiddenword.entity.ForbiddenWord;
import kr.hs.dgsw.jetty.forbiddenword.exception.ForbiddenWordStatusCode;
import kr.hs.dgsw.jetty.forbiddenword.repository.ForbiddenWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ForbiddenWordServiceImpl implements ForbiddenWordService {
    private final ForbiddenWordRepository forbiddenWordRepository;

    @Override
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

    @Override
    public void validate(String text) {
        if (containesForbiddenWords(text)) {
            throw ApplicationException.of(ForbiddenWordStatusCode.FORBIDDEN_WORD_DETECTED);
        }
    }

    @Override
    @Transactional
    public void addWords(ForbiddenWordRequest request) {
        forbiddenWordRepository.save(ForbiddenWord.create(request.word()));
    }

    @Override
    public List<ForbiddenWordResponse> getWords() {
        return forbiddenWordRepository.findAll().stream()
                .map(ForbiddenWordResponse::from)
                .toList();
    }

    @Override
    @Transactional
    public void deleteWords(Long id) {
        forbiddenWordRepository.deleteById(id);
    }

    private String normalize(String text) {
        return text.replace(" ", "").toLowerCase();
    }
}
