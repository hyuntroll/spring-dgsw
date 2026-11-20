package kr.hs.dgsw.jetty.persona.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
class PersonaControllerTest {

    private static final String BODY = """
            {"name":"%s","personality":"WARM","speechStyle":"CASUAL","greeting":"안녕!"}
            """;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void createReturns201() throws Exception {
        mvc.perform(post("/api/personas").contentType(MediaType.APPLICATION_JSON)
                        .content(BODY.formatted("테스트 캐릭터")))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.personaId").isNumber());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "12345678901"})
    void invalidNameReturns400(String name) throws Exception {
        mvc.perform(post("/api/personas").contentType(MediaType.APPLICATION_JSON)
                        .content(BODY.formatted(name)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.status").value(400));
    }

    @Test
    void missingPersonaReturns404() throws Exception {
        mvc.perform(get("/api/personas/{personaId}", Long.MAX_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error.status").value(404));
    }
}