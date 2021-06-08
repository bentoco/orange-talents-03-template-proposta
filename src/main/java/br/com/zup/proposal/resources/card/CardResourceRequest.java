package br.com.zup.proposal.resources.card;

import javax.validation.constraints.NotBlank;

public class CardResourceRequest {

    @NotBlank
    String documento;

    @NotBlank
    String nome;

    @NotBlank
    String idProposta;

    public CardResourceRequest (
            @NotBlank String documento ,
            @NotBlank String nome , @NotBlank String idProposta ) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento () {
        return documento;
    }

    public String getNome () {
        return nome;
    }

    public String getIdProposta () {
        return idProposta;
    }
}


