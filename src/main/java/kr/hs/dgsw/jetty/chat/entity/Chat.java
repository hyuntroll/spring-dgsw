package kr.hs.dgsw.jetty.chat.entity;

import jakarta.persistence.*;
import kr.hs.dgsw.jetty.common.BaseEntity;
import kr.hs.dgsw.jetty.conversation.entity.Conversation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@Entity
@Table( name = "chats" )
@NoArgsConstructor( access =  AccessLevel.PROTECTED)
public class Chat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @Lob
    @Column(nullable = false)
    private String userText;

    @Lob
    @Column(nullable = false)
    private String replyText;

    private Chat(Conversation conversation, String userText, String replyText) {
        this.conversation = Objects.requireNonNull(conversation);
        this.userText = Objects.requireNonNull(userText);
        this.replyText = Objects.requireNonNull(replyText);
    }

    public static Chat create(Conversation conversation, String userText, String replyText) {
        return new Chat(conversation, userText, replyText);
    }
}
