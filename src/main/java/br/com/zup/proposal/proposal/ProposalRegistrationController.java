package br.com.zup.proposal.proposal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping ( "/api/proposal" )
public class ProposalRegistrationController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerProposal (
            @Valid @RequestBody ProposalRequest request ,
            UriComponentsBuilder builder ) {
        Proposal proposal = request.toProposal();
        manager.persist(proposal);

        URI uri = builder.path("/api/proposal/{id}").buildAndExpand(proposal.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProposalResponse(proposal));
    }

    public static class ProposalResponse {

        private Long proposalId;

        private String message = "proposal successful stored";

        public ProposalResponse ( Proposal proposal ) {
            this.proposalId = proposal.getId();
            this.message = message;
        }

        public Long getProposalId () {
            return proposalId;
        }

        public String getMessage () {
            return message;
        }

        public void setProposalId ( Long proposalId ) {
            this.proposalId = proposalId;
        }
    }
}
