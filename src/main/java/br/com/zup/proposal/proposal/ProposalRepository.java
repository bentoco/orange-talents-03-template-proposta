package br.com.zup.proposal.proposal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    boolean existsByDocument ( String document );

    /**
     * @param proposalState ELEGIBLE OR NOT
     * @return fetch all proposals where cardNumber is null and status ELEGIBLE/OR NOT
     */
    List<Proposal> findAllByCardNumberIsNullAndStatusEquals ( ProposalState proposalState );
}
