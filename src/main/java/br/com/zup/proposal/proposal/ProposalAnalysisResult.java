package br.com.zup.proposal.proposal;

import javax.validation.constraints.NotBlank;

class ProposalAnalysisResult {

    @NotBlank
    String documento;

    @NotBlank
    String nome;

    @NotBlank
    String resultadoSolicitacao;

    @NotBlank
    String idProposta;

    public ProposaNAO_ELEGIVELlAnalysisResult (
            @NotBlank String documento ,
            @NotBlank String nome ,
            @NotBlank String resultadoSolicitacao ,
            @NotBlank String idProposta ) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public String getDocumento () {
        return documento;
    }

    public String getNome () {
        return nome;
    }

    public String getResultadoSolicitacao () {
        return resultadoSolicitacao;
    }

    public String getIdProposta () {
        return idProposta;
    }
}
