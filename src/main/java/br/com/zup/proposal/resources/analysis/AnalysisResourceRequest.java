package br.com.zup.proposal.resources.analysis;

import javax.validation.constraints.NotBlank;

public class AnalysisResourceRequest {

    @NotBlank
    String documento;

    @NotBlank
    String nome;

    @NotBlank
    String idProposta;

    public AnalysisResourceRequest (
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
