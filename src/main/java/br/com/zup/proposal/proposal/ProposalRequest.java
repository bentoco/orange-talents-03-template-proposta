package br.com.zup.proposal.proposal;

import br.com.zup.proposal.proposal.groups.LegalPerson;
import br.com.zup.proposal.proposal.groups.NaturalPerson;
import br.com.zup.proposal.proposal.groups.PersonType;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@GroupSequenceProvider ( value = PersonType.class )
public class ProposalRequest {

    @NotBlank
    @CPF ( groups = NaturalPerson.class )
    @CNPJ ( groups = LegalPerson.class )
    private String document;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String address;

    @NotNull
    @Positive
    private BigDecimal salary;

    @Deprecated
    public ProposalRequest () {
    }

    public ProposalRequest (
            @NotBlank @CPF ( groups = NaturalPerson.class ) @CNPJ ( groups = LegalPerson.class ) String document ,
            @NotBlank String name ,
            @NotBlank @Email String email ,
            @NotBlank String address ,
            @NotNull @Positive BigDecimal salary ) {
        this.document = document;
        this.name = name;
        this.email = email;
        this.address = address;
        this.salary = salary;
    }

    public String getDocument () {
        return document;
    }

    public String getName () {
        return name;
    }

    public String getEmail () {
        return email;
    }

    public String getAddress () {
        return address;
    }

    public BigDecimal getSalary () {
        return salary;
    }

    public Proposal toProposal () {
        return new Proposal(document, name, email, address, salary);
    }
}
