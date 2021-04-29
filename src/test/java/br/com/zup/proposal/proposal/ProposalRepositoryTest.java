package br.com.zup.proposal.proposal;

import br.com.zup.proposal.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class ProposalRepositoryTest {

    @Autowired
    private ProposalRepository repository;

    @Test
    @DisplayName ( " must fetch all elegible proposals where cardNumber is null" )
    @Transactional
    public void test1 () {
        Proposal
                proposal1 =
                new Proposal("70522107036" , "Foo" , "foo@mail.com" , "Stree of Life, 51" , BigDecimal.valueOf(2000d));
        Proposal
                proposal2 =
                new Proposal("79533882000" , "Foo" , "bar@mail.com" , "Stree of Life, 55" , BigDecimal.valueOf(5000d));
        Proposal
                proposal3 =
                new Proposal("02735822087" , "Foo" , "boo@mail.com" , "Stree of Life, 15" , BigDecimal.valueOf(4000d));

        proposal1.setStatus("SEM_RESTRICAO");

        proposal2.setStatus("COM_RESTRICAO");

        Card card3 = new Card("5409-8787-9540-5906");
        proposal3.setCard(card3);
        proposal3.setStatus("SEM_RESTRICAO");

        repository.save(proposal1);
        repository.save(proposal2);
        repository.save(proposal3);

        List<Proposal> result = repository.findAllByCardIdIsNullAndStatusEquals(ProposalState.ELEGIVEL);

        assertEquals(1 , result.size());
        assertEquals("70522107036", result.get(0).getDocument());
    }
}