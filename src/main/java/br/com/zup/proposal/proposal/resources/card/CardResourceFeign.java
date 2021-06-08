package br.com.zup.proposal.proposal.resources.card;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "card-resource", url = "${host.card-resources}")
public interface CardResourceFeign {

    @PostMapping
    CardResourceResult fetchCard(CardResourceRequest request);

    @PostMapping("/{id}/bloqueios")
    ResponseEntity<Void> lockCard(@PathVariable String id, @RequestBody CardResourceLockRequest request);
}
