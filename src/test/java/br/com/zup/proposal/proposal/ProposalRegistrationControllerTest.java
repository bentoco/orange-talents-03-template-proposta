package br.com.zup.proposal.proposal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@ActiveProfiles ( "test" )
@AutoConfigureMockMvc
class ProposalRegistrationControllerTest {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    @DisplayName ( " must register new proposal " )
    public void test1 () throws Exception {
        var proposalRequest = new ProposalRequest("43414120860" , "Foo Bar" , "foo@bar.com" , "Stree foo, 123" , BigDecimal.valueOf(2000d));
        var request = mapper.writeValueAsString(proposalRequest);

        var proposalResponse = new ProposalResponse(proposalRequest.toProposal());
        proposalResponse.setProposalId(1L);
        var response = mapper.writeValueAsString(proposalResponse);

        var uri = UriComponentsBuilder.fromUriString("http://localhost:8080/api/proposal").build().toUri();
        var location = UriComponentsBuilder.fromUriString("http://localhost:8080/api/proposal/1").build().toUri();

        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isCreated())
                .andExpect(header().string("location" , String.valueOf(location)))
                .andExpect(content().json(response));
    }

    @Test
    @Transactional
    @DisplayName ( " must not register new proposal " )
    public void test2 () throws Exception {
        var proposal = new Proposal("43414120860" , "Foo Bar" , "foo@bar.com" , "Stree foo, 123" , BigDecimal.valueOf(2000d));
        manager.persist(proposal);

        var proposalRequest = new ProposalRequest("43414120860" , "Foo Bar" , "foo@bar.com" , "Stree foo, 123" , BigDecimal.valueOf(2000d));
        var request = mapper.writeValueAsString(proposalRequest);

        var uri = UriComponentsBuilder.fromUriString("http://localhost:8080/api/proposal").build().toUri();

        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isUnprocessableEntity());

    }
}