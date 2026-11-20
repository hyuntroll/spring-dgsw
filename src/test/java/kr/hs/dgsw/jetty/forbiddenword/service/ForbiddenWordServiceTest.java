package kr.hs.dgsw.jetty.forbiddenword.service;

import kr.hs.dgsw.jetty.common.exception.ApplicationException;
import kr.hs.dgsw.jetty.forbiddenword.entity.ForbiddenWord;
import kr.hs.dgsw.jetty.forbiddenword.exception.ForbiddenWordStatusCode;
import kr.hs.dgsw.jetty.forbiddenword.repository.ForbiddenWordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ForbiddenWordServiceTest {

    @Mock
    private ForbiddenWordRepository forbiddenWordRepository;

    @InjectMocks
    private ForbiddenWordServiceImpl checker;

    @BeforeEach
    void setup() {
        given(forbiddenWordRepository.findAll()).willReturn(List.of(
                ForbiddenWord.create("바보"),
                ForbiddenWord.create("baka")
        ));
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
    void 금칙어가_있을_때_validate_exception() {
        assertThatThrownBy(() -> checker.validate("너는 바보다"))
                .isInstanceOf(ApplicationException.class)
                .hasMessage(ForbiddenWordStatusCode.FORBIDDEN_WORD_DETECTED.getMessage());
    }

    @Test
    void 금칙어가_없을_때_validate_exception() {
        assertThatCode(() -> checker.validate("난 너를 사랑해")).doesNotThrowAnyException();
    }

}
