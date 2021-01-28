package br.com.interview.technicalapp.recruiter.controller.v1;

import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.interview.technicalapp.candidate.service.CandidateService;
import br.com.interview.technicalapp.config.WebSecurityConfig;
import br.com.interview.technicalapp.content.service.ContentService;
import br.com.interview.technicalapp.question.service.QuestionService;
import br.com.interview.technicalapp.recruiter.controller.v1.dto.RecruiterRequest;
import br.com.interview.technicalapp.recruiter.service.RecruiterService;
import br.com.interview.technicalapp.user.service.UserService;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@SpringBootTest(classes = ApplicationContext.class)
@ActiveProfiles("test")
@Disabled
class RecruiterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecruiterService recruiterService;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private ContentService contentService;

    @MockBean
    private CandidateService candidateService;

    @MockBean
    private UserService userService;

    @BeforeAll
    static void setUp() {
    }

    @Test
    void givenRecruiter_whenSaveRecruiter_thenReturnJsonArray() throws Exception {
        RecruiterRequest joe = new RecruiterRequest();
        joe.setUsername("Joe");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(joe);

        given(recruiterService.save(Mockito.any())).willReturn(RecruiterRequest.render(joe));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/recruiters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)).andReturn();

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/recruiters")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is("Joe")))
                .andExpect(jsonPath("$.id", isA(UUID.class)))
                .andExpect(jsonPath("$.contents", emptyArray()))
                .andExpect(jsonPath("$.questions", emptyArray()));

        verify(recruiterService, VerificationModeFactory.times(1)).save(Mockito.any());
        reset(recruiterService);
    }

    @Test
    void getSimple() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/recruiters")).andReturn();

        Assertions.assertEquals(200, result.getResponse().getStatus());
    }
}
