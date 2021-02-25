package br.com.sicredi.voting.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.sicredi.voting.feature.SessionScenarioFactory;
import br.com.sicredi.voting.service.SessionService;
import br.com.sicredi.voting.utils.AbstractMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTest extends AbstractMapper {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionService sessionService;

    @Test
    public void insertSession_WhenSendASessionValid_ExpectedOk() throws Exception {
        when(sessionService.insertSession(any())).thenReturn(SessionScenarioFactory.SESSION_RESPONSE_CONTROLLER);
        mockMvc.perform(post("/sessions").contentType(MediaType.APPLICATION_JSON).content(asJsonString(SessionScenarioFactory.SESSION_REQUEST)))
        .andExpect(status().isCreated());
    }

    @Test
    public void listAllSessions() throws Exception{
        when(sessionService.listAllSessions()).thenReturn(SessionScenarioFactory.SESSION_RESPONSE_LIST);
        mockMvc.perform(get("/sessions/listSessions").contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(SessionScenarioFactory.SESSION_RESPONSE_LIST))).andExpect(status().isOk());
    }

    @Test
    public void getSession_WhenSendAIdValid_ExpectedOk() throws Exception{
        when(sessionService.getSession(any())).thenReturn(SessionScenarioFactory.SESSION_RESPONSE_CONTROLLER);
        mockMvc.perform(get("/sessions").param("sessionId", "1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(SessionScenarioFactory.SESSION_RESPONSE_CONTROLLER))).andExpect(status().isOk());
    }
}
