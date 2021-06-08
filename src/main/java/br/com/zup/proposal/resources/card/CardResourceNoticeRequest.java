package br.com.zup.proposal.resources.card;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class CardResourceNoticeRequest {

    @JsonProperty
    private String destino;

    @JsonProperty
    private LocalDate validoAte;

    public CardResourceNoticeRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }
}
