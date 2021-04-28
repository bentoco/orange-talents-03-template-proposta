package br.com.zup.proposal.proposal.resources.card;

import br.com.zup.proposal.proposal.resources.analysis.AnalysisResourceRequest;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotBlank;


@FeignClient ( value = "card-resource", url = "${host.card-resources}" )
public interface CardResourceFeign {

    @PostMapping
    ResponseEntity<CardResourceResult> fetchCard ( AnalysisResourceRequest request );
}

class CardResourceResult {

    private @NotBlank String id;

    @JsonCreator
    public CardResourceResult ( @JsonProperty ( "id" ) String id ) {
        this.id = id;
    }

    public String getId () {
        return id;
    }
}
