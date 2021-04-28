package br.com.zup.proposal.proposal;

import br.com.zup.proposal.proposal.resources.analysis.AnalysisResource;
import br.com.zup.proposal.proposal.resources.analysis.AnalysisResourceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping ( "/api/proposal" )
public class ProposalRegistrationController {

    @Autowired
    private final ProposalRepository repository;
    private final AnalysisResource analysisResource;

    public ProposalRegistrationController (
            ProposalRepository repository ,
            AnalysisResource analysisResource ) {
        this.repository = repository;
        this.analysisResource = analysisResource;
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

            AnalysisResourceResult result = analysisResource.financialEvaluantion(proposal);
            proposal.setStatus(result.getResultadoSolicitacao());
            repository.save(proposal);

            URI uri = builder.path("/api/proposal/{id}").buildAndExpand(proposal.getId()).toUri();
            return ResponseEntity.created(uri).body(new ProposalCreated(proposal));
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping ( "/{id}" )
    public ResponseEntity<?> readProposal ( @PathVariable Long id ) {
        Optional<Proposal> maybeProposal = repository.findById(id);
        Optional<ResponseEntity<Proposal>> responseEntity = maybeProposal.map(ResponseEntity::ok);
        return responseEntity.orElseGet(() -> ResponseEntity.notFound().build());
//        if(maybeProposal.isPresent()){
//            return ResponseEntity.ok(new ProposalResponse(maybeProposal.get()));
//        }
//        return ResponseEntity.notFound().build();
    }
}

class ProposalCreated {

    private Long proposalId;

    private String message = "proposal successful stored";

    public ProposalCreated ( Proposal proposal ) {
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

class ProposalResponse {

    private final Long proposalId;
    private final String document;
    private final String name;
    private final String email;
    private final String address;
    private final BigDecimal salary;
    private final String status;
    private final String cardNumber;

    ProposalResponse ( Proposal proposal ) {
        this.proposalId = proposal.getId();
        this.document = proposal.getDocument();
        this.name = proposal.getName();
        this.email = proposal.getEmail();
        this.address = proposal.getAddress();
        this.salary = proposal.getSalary();
        this.status = proposal.getStatus().toString();
        this.cardNumber = proposal.getCardNumber();
    }

    public Long getProposalId () {
        return proposalId;
    }

    public String getDocument () {
        return document;
    }

    public String getName () {
        return name;
    }

    public String getEmail () {
        return email;
    }

    public String getAddress () {
        return address;
    }

    public BigDecimal getSalary () {
        return salary;
    }

    public String getStatus () {
        return status;
    }

    public String getCardNumber () {
        return cardNumber;
    }
}
