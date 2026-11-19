package kr.hs.dgsw.jetty.forbiddenword.entity;

import jakarta.persistence.*;
import kr.hs.dgsw.jetty.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@Entity
@Table(name = "forbidden_words")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ForbiddenWord extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String word;

    private ForbiddenWord(String word) {
        this.word = Objects.requireNonNull(word);
    }

    public static ForbiddenWord create(String word) {
        return new ForbiddenWord(word);
    }
}
