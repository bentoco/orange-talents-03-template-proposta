package br.com.zup.proposal.card.biometry;

import br.com.zup.proposal.config.validators.MustBeBase64;
import br.com.zup.proposal.config.validators.MustBeUnique;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class BiometryRequest {

    @NotBlank
    @JsonProperty
    @MustBeUnique(field = "fingerprint", klazz = Biometry.class)
    @MustBeBase64(field = "fingerprint", klazz = Biometry.class)
    private String fingerprint;

    @JsonCreator
    public BiometryRequest ( @JsonProperty ( value = "fingerprint" ) @NotBlank String fingerprint ) {
        this.fingerprint = fingerprint;
    }

    public Biometry toBiometric () {
        return new Biometry(this.fingerprint);
    }
}
