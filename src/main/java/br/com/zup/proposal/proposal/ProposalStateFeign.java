package br.com.zup.proposal.proposal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient ( value = "proposal-state", url = "${URI-ANALYSIS-RESOURCES}" )
public interface ProposalStateFeign {

    @PostMapping ( value = "/api/solicitacao" )
    ResponseEntity<ProposalAnalysisResult> proposalAnalysis ( ProposalAnalysisRequest request );
}