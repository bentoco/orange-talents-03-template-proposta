package br.com.zup.proposal.proposal.resources.card;

import br.com.zup.proposal.proposal.Proposal;
import br.com.zup.proposal.proposal.ProposalRepository;
import br.com.zup.proposal.proposal.ProposalState;
import br.com.zup.proposal.proposal.resources.analysis.AnalysisResourceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class CardResourceSchedule {

    @Autowired
    private final ProposalRepository repository;
    private final CardResourceFeign feign;

    public CardResourceSchedule (
            ProposalRepository repository ,
            CardResourceFeign feign ) {
        this.repository = repository;
        this.feign = feign;
    }

    @Scheduled ( fixedDelay = 900000 ) // 15 min
    @Transactional
    public void associateCardToProposal () {
        List<Proposal> hasAvailables = repository.findAllByCardNumberIsNullAndStatusEquals(ProposalState.ELEGIVEL);
        if (!hasAvailables.isEmpty()) {
            hasAvailables.forEach(proposal -> {
                AnalysisResourceRequest request = new AnalysisResourceRequest(proposal.getDocument() ,
                        proposal.getName() ,
                        proposal.getId().toString());
                ResponseEntity<CardResourceResult> feignResult = feign.fetchCard(request);
                String cardNumber = Objects.requireNonNull(feignResult.getBody()).getId();
                proposal.setCardNumber(cardNumber);
            });
            repository.saveAll(hasAvailables);
        }
    }
}
