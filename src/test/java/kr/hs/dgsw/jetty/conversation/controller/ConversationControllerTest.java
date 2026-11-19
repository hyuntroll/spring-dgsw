package kr.hs.dgsw.jetty.conversation.controller;

import kr.hs.dgsw.jetty.conversation.repository.ConversationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class ConversationControllerTest {

    @Autowired
    private ConversationRepository conversationRepository;

    @Test
    void name() {
    }
}