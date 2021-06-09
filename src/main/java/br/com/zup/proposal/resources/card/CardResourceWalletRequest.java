package br.com.zup.proposal.resources.card;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardResourceWalletRequest {

    @JsonProperty
    private String email;

    @JsonProperty
    private String carteira;

    public CardResourceWalletRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }
}
