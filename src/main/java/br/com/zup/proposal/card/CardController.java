package br.com.zup.proposal.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping ( "/api/card" )
public class CardController {

    @Autowired
    private final CardRepository repository;

    public CardController ( CardRepository repository ) {
        this.repository = repository;
    }

    @PostMapping ( "/{cardId}" )
    @Transactional
    public ResponseEntity<?> registerBiometrics (
            @PathVariable String cardId ,
            @RequestBody @Valid BiometryRequest request ,
            UriComponentsBuilder builder ) {

        Optional<Card> hasCard = repository.findById(cardId);
        if (hasCard.isPresent()) {
            Card card = hasCard.get();
            Biometry biometry = request.toBiometric();
            card.addBiometry(biometry);
            repository.save(card);

            URI uri = builder.path("/{id}").build(biometry.getId());
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.notFound().build();
    }
}

