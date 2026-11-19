INSERT INTO forbidden_words (word, created_at, updated_at) VALUES ('바보', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO forbidden_words (word, created_at, updated_at) VALUES ('멍청이', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO conversations(personaId)
VALUES
    (1),
    (2),
    (3),
    (4);

INSERT INTO chats(conversation_id, userText, replyText)
VALUES
    (1, "만나서 반가워", "나도 만나서 반가워"),
    (2, "만나서 반가워", "나도 만나서 반가워");