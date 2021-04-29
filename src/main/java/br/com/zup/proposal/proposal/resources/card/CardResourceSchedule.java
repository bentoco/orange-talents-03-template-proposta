package br.com.zup.proposal.proposal.resources.card;

import br.com.zup.proposal.card.Card;
import br.com.zup.proposal.proposal.Proposal;
import br.com.zup.proposal.proposal.ProposalRepository;
import br.com.zup.proposal.proposal.ProposalState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    @Scheduled ( fixedDelay = 5000 ) // 15 min
    @Transactional
    public void associateCardToProposal () {
        List<Proposal> hasAvailables = repository.findAllByCardIdIsNullAndStatusEquals(ProposalState.ELEGIVEL);
        if (!hasAvailables.isEmpty()) {
            hasAvailables.forEach(proposal -> {
                CardResourceRequest
                        request =
                        new CardResourceRequest(proposal.getDocument() , proposal.getName() , proposal.getId().toString());
                CardResourceResult result = feign.fetchCard(request);
                Card card = result.toCard();
                proposal.setCard(card);
            });
            repository.saveAll(hasAvailables);
        }
    }
}
