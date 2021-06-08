package br.com.zup.proposal.card;

import br.com.zup.proposal.card.biometry.Biometry;
import br.com.zup.proposal.card.biometry.BiometryRequest;
import br.com.zup.proposal.card.locks.Lock;
import br.com.zup.proposal.card.travel.TravelNotice;
import br.com.zup.proposal.card.travel.TravelNoticeRequest;
import br.com.zup.proposal.proposal.resources.card.CardResourceFeign;
import br.com.zup.proposal.proposal.resources.card.CardResourceLockRequest;
import feign.FeignException;
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
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    private final CardRepository repository;
    private final CardResourceFeign feign;

    public CardController(CardRepository repository, CardResourceFeign feign) {
        this.repository = repository;
        this.feign = feign;
    }

    @PostMapping("/{cardId}")
    @Transactional
    public ResponseEntity<?> registerBiometries(
            @PathVariable String cardId,
            @RequestBody @Valid BiometryRequest request,
            UriComponentsBuilder builder) {

        Optional<Card> hasCard = repository.findByCardNumber(cardId);
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

    @PostMapping("/{cardNumber}/lock")
    @Transactional
    public ResponseEntity<Void> lockCard(@PathVariable String cardNumber, HttpServletRequest request) {

        String userAgent = request.getHeader("User-Agent");
        String ipClient = request.getRemoteAddr();

        Optional<Card> hasCard = repository.findByCardNumber(cardNumber);
        if (hasCard.isPresent()) {
            try {
                feign.lockCard(cardNumber, new CardResourceLockRequest());
                Card card = hasCard.get();
                Lock lock = new Lock(card, userAgent, ipClient);
                card.addLock(lock);
                repository.save(card);
            } catch (FeignException e) {
                if (e.status() == 400) {
                    return ResponseEntity.badRequest().build();
                }
                if (e.status() == 422) {
                    return ResponseEntity.unprocessableEntity().build();
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{cardNumber}/travel-notice")
    @Transactional
    public ResponseEntity<?> travelNotice(@PathVariable String cardNumber, @RequestBody @Valid TravelNoticeRequest request, HttpServletRequest httpServletRequest) {

        String userAgent = httpServletRequest.getHeader("User-Agent");
        String ipClient = httpServletRequest.getRemoteAddr();

        Optional<Card> hasCard = repository.findByCardNumber(cardNumber);
        if (hasCard.isPresent()) {
            Card card = hasCard.get();
            TravelNotice travelNotice = request.toTravel(card, ipClient, userAgent);
            card.addTravelNotice(travelNotice);
            repository.save(card);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

