package kr.hs.dgsw.jetty.controller;

import kr.hs.dgsw.jetty.repository.ChatRepository;
import kr.hs.dgsw.jetty.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class ChatControllerTest {
    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ChatRepository chatRepository;




}