package br.com.zup.proposal.card;

import br.com.zup.proposal.card.biometry.Biometry;
import br.com.zup.proposal.card.biometry.BiometryRequest;
import br.com.zup.proposal.card.locks.Lock;
import br.com.zup.proposal.proposal.resources.card.CardResourceFeign;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping ( "/api/card" )
public class CardController {

    @Autowired
    private final CardRepository repository;
    private final CardResourceFeign feign;

    public CardController ( CardRepository repository , CardResourceFeign feign ) {
        this.repository = repository;
        this.feign = feign;
    }

    @PostMapping ( "/{cardId}" )
    @Transactional
    public ResponseEntity<?> registerBiometries (
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

    @PostMapping ( "/{cardNumber}/lock" )
    @Transactional
    public ResponseEntity<Void> lockCard ( @PathVariable String cardNumber , HttpServletRequest request ) {

        String userAgent = request.getHeader("User-Agent");
        String ipClient = request.getRemoteAddr();

        Optional<Card> hasCard = repository.findByCardNumber(cardNumber);
        if (hasCard.isPresent()) {
            Response response = feign.lockCard(cardNumber);
            if (response.status() == 200) {
                Card card = hasCard.get();
                Lock lock = new Lock(card , userAgent , ipClient);
                card.addLock(lock);
                repository.save(card);

                return ResponseEntity.ok().build();
            } else if (response.status() == 422) {
                return ResponseEntity.unprocessableEntity().build();
            }
        }
        return ResponseEntity.notFound().build();

    }
}

