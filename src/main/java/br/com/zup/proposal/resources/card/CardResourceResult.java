package br.com.zup.proposal.resources.card;

import br.com.zup.proposal.card.Card;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CardResourceResult {

    @JsonProperty
    private @NotBlank String id;

    @JsonCreator
    public CardResourceResult ( @JsonProperty ( "id" ) @NotBlank String id ) {
        this.id = id;
    }

    public Card toCard () {
        return new Card(this.id);
    }
}


