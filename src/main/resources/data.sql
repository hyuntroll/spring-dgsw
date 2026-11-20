INSERT INTO forbidden_words (word, created_at, updated_at) VALUES ('바보', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO forbidden_words (word, created_at, updated_at) VALUES ('멍청이', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO persona (name, personality, speech_style, greeting, created_at, updated_at)
VALUES ('제티', 'WARM', 'CASUAL', '안녕!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
        ('루미', 'PLAYFUL', 'POLITE', '안녕하세요!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
        ('하루', 'COOL', 'POLITE', '안녕하세요. 오늘은 어떤 하루였나요?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO conversations (id, persona_id, created_at, updated_at)
SELECT
    '11111111-1111-4111-8111-111111111111',
    persona_id,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
FROM persona
WHERE name = '제티';

INSERT INTO chats (conversation_id, user_text, reply_text, created_at, updated_at)
SELECT
    '11111111-1111-4111-8111-111111111111',
    NULL,
    greeting,
    CURRENT_TIMESTAMP, 
    CURRENT_TIMESTAMP
FROM persona WHERE name = '제티';

INSERT INTO conversations (id, persona_id, created_at, updated_at)
SELECT
    '22222222-2222-4222-8222-222222222222',
    persona_id,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
FROM persona WHERE name = '제티';

INSERT INTO chats (conversation_id, user_text, reply_text, created_at, updated_at)
SELECT
    '22222222-2222-4222-8222-222222222222',
    NULL,
    greeting,
    DATEADD('SECOND', -6, CURRENT_TIMESTAMP),
    DATEADD('SECOND', -6, CURRENT_TIMESTAMP)
FROM persona WHERE name = '제티';

INSERT INTO chats (conversation_id, user_text, reply_text, created_at, updated_at) VALUES
    ('22222222-2222-4222-8222-222222222222', '요즘 취업 준비 때문에 고민이 많아. 내가 잘할 수 있을지 걱정돼.', '그런 걱정하는 거 정말 이해돼. 네 강점이 뭔지 하나씩 정리해보면 생각보다 잘할 수 있는 부분이 많을 거야. 이력서·자기소개서·면접 중 어디부터 도와줄까?', DATEADD('SECOND', -5, CURRENT_TIMESTAMP), DATEADD('SECOND', -5, CURRENT_TIMESTAMP)),
    ('22222222-2222-4222-8222-222222222222', '개발자로 취업하고 싶은데 아직 실력이 부족한 것 같아서 자신이 없어.', '그럴 수 있어, 많은 사람이 비슷하게 느껴서 걱정해도 괜찮아.  
기본기(자료구조·알고리즘) 다지고 작은 포트폴리오 프로젝트 1~2개 완성하면서 지원하고 면접 연습하면 자신감 금방 붙을 거야.  
어떤 스택(프론트/백/풀스택, 사용하는 언어 등)을 목표로 하는지 말해줘, 거기에 맞춰 공부 계획이나 프로젝트 아이디어 도와줄게.', DATEADD('SECOND', -4, CURRENT_TIMESTAMP), DATEADD('SECOND', -4, CURRENT_TIMESTAMP)),
    ('22222222-2222-4222-8222-222222222222', '포트폴리오에 프로젝트를 넣고 싶은데 어떤 점을 보여주는 게 좋을까?', '포트폴리오에는 ''무슨 문제를 해결했는지'', ''네가 맡은 역할과 기여'', ''사용한 기술 스택과 설계(아키텍처)'', ''동작하는 데모/배포 링크'', ''코드 품질·테스트·CI'', 그리고 가능하면 성과(속도 개선 수치나 사용자 지표)를 보여주는 게 좋아.  
백엔드면 API 설계·DB·성능·인증을, 프론트면 반응형 UI·상태관리·접근성·UX를 강조하고, 풀스택이면 둘 다 + 깃허브·README에 문제 해결 과정과 배운 점을 정리해; 원하는 스택 말해주면 구체적인 프로젝트 아이디어도 줄게.', DATEADD('SECOND', -3, CURRENT_TIMESTAMP), DATEADD('SECOND', -3, CURRENT_TIMESTAMP)),
    ('22222222-2222-4222-8222-222222222222', '면접에서 모르는 질문을 받으면 어떻게 대답해야 할까?', '모르는 질문이면 뻥치지 말고 솔직히 모른다고 인정한 다음, 관련 지식으로 유추하거나 문제를 어떻게 풀 건지 단계적으로 설명해.  
예를 들어 “그 부분은 정확히는 모릅니다만 제가 아는 건 …이고, 이 문제는 이렇게 접근해볼 것 같습니다. 면접 끝난 뒤 정확히 확인해서 답변 드려도 될까요?”처럼 말하면 좋아.', DATEADD('SECOND', -2, CURRENT_TIMESTAMP), DATEADD('SECOND', -2, CURRENT_TIMESTAMP)),
    ('22222222-2222-4222-8222-222222222222', '고마워. 오늘부터 할 수 있는 일 하나만 추천해 줄래?', '오늘 당장 할 수 있는 건 깃허브 리포지터리 하나 만들고 README에 만들고 싶은 포트폴리오 프로젝트의 목표·주요 기능·사용 기술·간단 일정 적어두는 거야. 30분~1시간이면 되고 작성하면 내가 같이 봐줄게.', DATEADD('SECOND', -1, CURRENT_TIMESTAMP), DATEADD('SECOND', -1, CURRENT_TIMESTAMP));
