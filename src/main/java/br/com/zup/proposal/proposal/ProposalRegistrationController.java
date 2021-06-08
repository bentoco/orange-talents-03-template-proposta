package br.com.zup.proposal.proposal;

import br.com.zup.proposal.proposal.resources.analysis.AnalysisResource;
import br.com.zup.proposal.proposal.resources.analysis.AnalysisResourceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/proposal")
public class ProposalRegistrationController {

    @Autowired
    private final ProposalRepository repository;
    private final AnalysisResource analysisResource;

    public ProposalRegistrationController(
            ProposalRepository repository,
            AnalysisResource analysisResource) {
        this.repository = repository;
        this.analysisResource = analysisResource;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerProposal(
            @Valid @RequestBody ProposalRequest request,
            UriComponentsBuilder builder) throws Exception {

        boolean hasProposal = repository.existsByDocument(request.getDocument());
        if (!hasProposal) {
            Proposal proposal = request.toProposal();
            repository.save(proposal);

            AnalysisResourceResult result = analysisResource.financialEvaluantion(proposal);
            proposal.setStatus(result.getResultadoSolicitacao());
            repository.save(proposal);

            URI uri = builder.path("/api/proposal/{id}").buildAndExpand(proposal.getId()).toUri();
            return ResponseEntity.created(uri).body(new ProposalCreated(proposal));
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readProposal(@PathVariable Long id) {
        Optional<Proposal> maybeProposal = repository.findById(id);

        if (maybeProposal.isPresent()) {
            if (maybeProposal.get().getCard() == null) {
                return ResponseEntity.unprocessableEntity().body("in process, try again later");
            }

            if (maybeProposal.get().getStatus() == ProposalState.NAO_ELEGIVEL) {
                return ResponseEntity.unprocessableEntity().body("proposal with status ineligible");
            }

            Proposal proposal = maybeProposal.get();
            return ResponseEntity.ok().body(new ProposalResponse(proposal));
        }
        return ResponseEntity.notFound().build();
    }
}

class ProposalCreated {

    private Long proposalId;

    private String message = "proposal successful stored";

    public ProposalCreated(Proposal proposal) {
        this.proposalId = proposal.getId();
        this.message = message;
    }

    public Long getProposalId() {
        return proposalId;
    }

    public String getMessage() {
        return message;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }
}
