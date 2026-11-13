package kr.hs.dgsw.jetty.forbiddenword.service;

import kr.hs.dgsw.jetty.forbiddenword.config.ForbiddenWordProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForbiddenWordChecker {
    private final ForbiddenWordProperties properties;

    public boolean containesForbiddenWords(String text) {
        if (!properties.isEnabled() || text == null) {
            return false;
        }

        String target = normalize(text);

        for (String word : properties.getWords()) {
            if (target.contains(word)) {
                return true;
            }
        }
        return false;
    }

    private String normalize(String text) {
        return text.replace(" ", "").toLowerCase();
    }
}
