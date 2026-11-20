package kr.hs.dgsw.jetty.conversation.controller;

import kr.hs.dgsw.jetty.conversation.dto.response.CreateConversationResponse;
import kr.hs.dgsw.jetty.conversation.service.ConversationService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import tools.jackson.databind.json.JsonMapper;

import java.util.Map;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConversationController.class)
class ConversationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonMapper jsonMapper;

    @MockitoBean
    private ConversationService conversationService;

    private static final String url = "/conversations";

    @Test
    void create_success() throws Exception {
        // given
        Long personaId = 1L;
        UUID conversationId = UUID.fromString("22222222-2222-4222-8222-222222222222");

        String request = jsonMapper.writeValueAsString(
                Map.of("personaId", personaId)
        );

        BDDMockito.given(conversationService.createConversation(personaId))
                .willReturn(new CreateConversationResponse(conversationId));

        // when
        ResultActions result = mockMvc.perform(post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request)
        );

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.conversationId").value(conversationId.toString()));

    }

    @Test
    void create_fail() throws Exception {
        // when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
        );

        result.andExpect(status().isBadRequest());
    }
}