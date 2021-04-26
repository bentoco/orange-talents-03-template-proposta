package br.com.zup.proposal.proposal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProposalAnalysis {

    @Autowired
    private final ProposalStateFeign feign;

    public ProposalAnalysis ( ProposalStateFeign feign ) {
        this.feign = feign;
    }

    private final Logger logger = LoggerFactory.getLogger(ProposalRegistrationController.class);

    public ProposalAnalysisResult financialEvaluantion ( Proposal proposal , ProposalRepository repository ) throws Exception {
        try {
            ProposalAnalysisRequest
                    analysisRequest =
                    new ProposalAnalysisRequest(proposal.getDocument() , proposal.getName() , proposal.getId().toString());
                    return feign.proposalAnalysis(analysisRequest).getBody();
        } catch (Exception e) {
            logger.error("something wrong has happened " , e);
        }
        return null;
    }
}
