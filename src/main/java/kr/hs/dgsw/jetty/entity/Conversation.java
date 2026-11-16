package kr.hs.dgsw.jetty.entity;

import jakarta.persistence.*;
import kr.hs.dgsw.jetty.common.BaseEntity;
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

    @Column(nullable = false)
    private Long personaId;

    private Conversation(Long personaId) {
        this.personaId = Objects.requireNonNull(personaId);
    }

    public static Conversation create(Long personaId) {
        return new Conversation(personaId);
    }
}
