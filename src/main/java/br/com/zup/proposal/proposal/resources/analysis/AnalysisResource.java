package br.com.zup.proposal.proposal.resources.analysis;

import br.com.zup.proposal.proposal.*;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnalysisResource {

    @Autowired
    private final AnalysisResourceFeign feign;

    public AnalysisResource ( AnalysisResourceFeign feign ) {
        this.feign = feign;
    }

    private final Logger logger = LoggerFactory.getLogger(ProposalRegistrationController.class);

    public AnalysisResourceResult financialEvaluantion ( Proposal proposal ) throws Exception {
        try {
            AnalysisResourceRequest
                    analysisRequest =
                    new AnalysisResourceRequest(proposal.getDocument() , proposal.getName() , proposal.getId().toString());
            return feign.proposalAnalysis(analysisRequest).getBody();
        } catch (FeignException.UnprocessableEntity e) {
            return new AnalysisResourceResult(proposal.getDocument() , proposal.getName() , "COM_RESTRICAO" , proposal.getId().toString());
        }
    }
}
