package br.com.zup.proposal.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping ( "/api/proposal" )
public class ProposalRegistrationController {

    @Autowired
    private final ProposalRepository repository;
    private final ProposalAnalysis proposalAnalysis;

    public ProposalRegistrationController (
            ProposalRepository repository ,
            ProposalAnalysis proposalAnalysis ) {
        this.repository = repository;
        this.proposalAnalysis = proposalAnalysis;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerProposal (
            @Valid @RequestBody ProposalRequest request ,
            UriComponentsBuilder builder ) throws Exception {

        boolean hasProposal = repository.existsByDocument(request.getDocument());
        if (!hasProposal) {
            Proposal proposal = request.toProposal();
            repository.save(proposal);

            ProposalAnalysisResult result = proposalAnalysis.financialEvaluantion(proposal , repository);
            proposal.setStatus(result.getResultadoSolicitacao());
            repository.save(proposal);

            URI uri = builder.path("/api/proposal/{id}").buildAndExpand(proposal.getId()).toUri();
            return ResponseEntity.created(uri).body(new ProposalResponse(proposal));
        }
        return ResponseEntity.unprocessableEntity().build();
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
