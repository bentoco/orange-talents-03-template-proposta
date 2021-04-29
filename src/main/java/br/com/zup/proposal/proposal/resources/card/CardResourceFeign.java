package br.com.zup.proposal.proposal.resources.card;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient ( value = "card-resource", url = "${host.card-resources}" )
public interface CardResourceFeign {

    @PostMapping
    CardResourceResult fetchCard ( CardResourceRequest request );
}