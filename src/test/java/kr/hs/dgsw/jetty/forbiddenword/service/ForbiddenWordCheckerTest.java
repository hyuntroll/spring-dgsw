package kr.hs.dgsw.jetty.forbiddenword.service;

import kr.hs.dgsw.jetty.common.exception.ApplicationException;
import kr.hs.dgsw.jetty.forbiddenword.config.ForbiddenWordProperties;
import kr.hs.dgsw.jetty.forbiddenword.exception.ForbiddenWordStatusCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ForbiddenWordCheckerTest {

    private ForbiddenWordProperties properties;
    private ForbiddenWordChecker checker;

    @BeforeEach
    void setup() {
        properties = new ForbiddenWordProperties();
        properties.setWords(List.of("바보", "baka"));
        checker = new ForbiddenWordChecker(properties);
    }

    @Test
    void 금칙어가_있으면_true() {
        assertThat(checker.containesForbiddenWords("너는 바보야.")).isTrue();
    }

    @Test
    void 금칙어가_없으면_false() {
        assertThat(checker.containesForbiddenWords("난 너를 사랑해")).isFalse();
    }

    @Test
    void 띄어쓰기_대소문자_식별() {
        assertThat(checker.containesForbiddenWords("이 바 보야")).isTrue();
        assertThat(checker.containesForbiddenWords("BaKa")).isTrue();
        assertThat(checker.containesForbiddenWords("B aK A")).isTrue();
    }

    @Test
    void 검사_비활성화() {
        properties.setEnabled(false);

        assertThat(checker.containesForbiddenWords("바보야")).isFalse();
    }

    @Test
    void 금칙어가_있을_때_validate_exception() {
        assertThatThrownBy(() -> checker.validate("너는 바보다"))
                .isInstanceOf(ApplicationException.class)
                .hasMessage(ForbiddenWordStatusCode.FORBIDDEN_WORD_DETECTED.getMessage());
    }

    @Test
    void 금칙어가_없을_때_validate_exception() {
        assertThatThrownBy(() -> checker.validate("난 너를 사랑해")).doesNotThrowAnyException();
    }

}