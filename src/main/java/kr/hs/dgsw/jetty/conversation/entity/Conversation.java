package kr.hs.dgsw.jetty.conversation.entity;

import jakarta.persistence.*;
import kr.hs.dgsw.jetty.common.BaseEntity;
import kr.hs.dgsw.jetty.persona.entity.Persona;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Getter
@Entity
@Table( name = "conversations" )
@NoArgsConstructor( access =  AccessLevel.PROTECTED)
public class Conversation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    private Conversation(Persona persona) {
        this.persona = Objects.requireNonNull(persona);
    }

    public static Conversation create(Persona persona) {
        return new Conversation(persona);
    }
}
