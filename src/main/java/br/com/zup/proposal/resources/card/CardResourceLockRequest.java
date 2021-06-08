package br.com.zup.proposal.resources.card;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class CardResourceLockRequest {

    @JsonProperty
    private final String sistemaResponsavel;

    public CardResourceLockRequest() {
        this.sistemaResponsavel = "PROPOSAL-API";
    }
}
