package br.com.sicredi.voting.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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

import br.com.sicredi.voting.feature.AssociateScenarioFactory;
import br.com.sicredi.voting.service.AssociateService;
import br.com.sicredi.voting.utils.AbstractMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(AssociateController.class)
public class AssociateControllerTest extends AbstractMapper {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssociateService service;
    
    @Test
    public void insertAssociate_WhenSendValidAssociate_ExpectedOk() throws Exception {
        when(service.insertAssociate(any())).thenReturn(AssociateScenarioFactory.ASSOCIATE_RESPONSE);
        mockMvc.perform(post("/associates").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(AssociateScenarioFactory.ASSOCIATE_REQUEST))).andExpect(status().isCreated());
    }

}
