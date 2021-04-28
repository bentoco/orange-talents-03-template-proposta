package br.com.zup.proposal.proposal.groups;

import br.com.zup.proposal.proposal.ProposalRequest;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class PersonType implements DefaultGroupSequenceProvider<ProposalRequest> {
    @Override public List<Class<?>> getValidationGroups ( ProposalRequest request ) {
        List<Class<?>> personType = new ArrayList<>();
        personType.add(ProposalRequest.class);

        if (request != null) {
            if (request.getDocument().length() == 18) {
                personType.add(LegalPerson.class);
            } else if (request.getDocument().length() == 14) {
                personType.add(NaturalPerson.class);
            }
        }
        return personType;
    }
}
