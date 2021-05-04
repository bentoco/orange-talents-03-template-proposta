package br.com.zup.proposal.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {
    Optional<Card> findByCardNumber ( String cardNumber );
}
