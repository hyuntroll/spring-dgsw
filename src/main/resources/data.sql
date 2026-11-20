-- INSERT INTO forbidden_words (word, created_at, updated_at) VALUES ('바보', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- INSERT INTO forbidden_words (word, created_at, updated_at) VALUES ('멍청이', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--
-- INSERT INTO conversations(personaId)
-- VALUES
--     (1),
--     (2),
--     (3),
--     (4);
--
-- INSERT INTO chats(conversation_id, userText, replyText)
-- VALUES
--     (1, "만나서 반가워", "나도 만나서 반가워"),
--     (2, "만나서 반가워", "나도 만나서 반가워");


-- 페르소나
INSERT INTO persona
(name, personality, speech_style, greeting, created_at, updated_at)
VALUES
    ('제티', 'WARM', 'POLITE', '안녕하세요! 오늘 하루는 어떠셨어요?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('하루', 'PLAYFUL', 'CASUAL', '안녕! 오늘 뭐 하고 놀까?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('시온', 'COOL', 'CASUAL', '왔어? 오늘은 무슨 일이야?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 대화방
INSERT INTO conversations
(id, persona_id, created_at, updated_at)
SELECT
    CAST('11111111-1111-4111-8111-111111111111' AS UUID),
    id,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
FROM persona
WHERE name = '제티';

INSERT INTO conversations
(id, persona_id, created_at, updated_at)
SELECT
    CAST('22222222-2222-4222-8222-222222222222' AS UUID),
    id,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
FROM persona
WHERE name = '하루';

INSERT INTO conversations
(id, persona_id, created_at, updated_at)
SELECT
    CAST('33333333-3333-4333-8333-333333333333' AS UUID),
    id,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
FROM persona
WHERE name = '시온';