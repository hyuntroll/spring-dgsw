package kr.hs.dgsw.jetty.persona.entity;

import jakarta.persistence.*;
import kr.hs.dgsw.jetty.common.BaseEntity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "persona")
public class Persona extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
