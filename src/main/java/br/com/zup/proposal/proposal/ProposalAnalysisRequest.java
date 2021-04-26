package br.com.zup.proposal.proposal;

import javax.validation.constraints.NotBlank;

class ProposalAnalysisRequest {

    @NotBlank
    String documento;

    @NotBlank
    String nome;

    @NotBlank
    String idProposta;

    public ProposalAnalysisRequest (
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
