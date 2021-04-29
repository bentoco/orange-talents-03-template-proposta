package br.com.zup.proposal.proposal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    boolean existsByDocument ( String document );

    /**
     * @param proposalState ELEGIBLE OR NOT
     * @return fetch all proposals where cardNumber is null and status ELEGIBLE/OR NOT
     */
    List<Proposal> findAllByCardIdIsNullAndStatusEquals ( ProposalState proposalState );

    /**
     * @param id cardId
     * @return proposal if card has already been pegged
     */
    Optional<Proposal> findByCardId ( String id );
}
