INSERT INTO forbidden_words (word, created_at, updated_at) VALUES ('바보', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO forbidden_words (word, created_at, updated_at) VALUES ('멍청이', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO persona (name, personality, speech_style, greeting, created_at, updated_at)
VALUES ('제티', 'WARM', 'CASUAL', '안녕!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
        ('루미', 'PLAYFUL', 'POLITE', '안녕하세요!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);