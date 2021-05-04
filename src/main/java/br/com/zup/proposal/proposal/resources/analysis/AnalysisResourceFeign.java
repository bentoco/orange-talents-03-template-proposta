package br.com.zup.proposal.proposal.resources.analysis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient ( value = "analysis-resource", url = "${host.analysis-resources}" )
public interface AnalysisResourceFeign {

    @PostMapping()
    ResponseEntity<AnalysisResourceResult> proposalAnalysis ( AnalysisResourceRequest request );
}

