package kr.hs.dgsw.jetty.persona.entity;

import jakarta.persistence.*;
import kr.hs.dgsw.jetty.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "persona")
public class Persona extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personaId;

    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "personality", length = 50, nullable = false)
    private Personality personality;

    @Enumerated(EnumType.STRING)
    @Column(name = "speech_style", length = 50, nullable = false)
    private SpeechStyle speechStyle;

    @Column(name = "greeting", length = 200, nullable = false)
    private String greeting;

    public Persona(String name, Personality personality, SpeechStyle speechStyle, String greeting) {
        this.name = name;
        this.personality = personality;
        this.speechStyle = speechStyle;
        this.greeting = greeting;
    }
}
