package br.com.zup.proposal.proposal;

import java.math.BigDecimal;

public class ProposalResponse {

    private Long proposalId;
    private String document;
    private String name;
    private String email;
    private String address;
    private BigDecimal salary;
    private String status;
    private String cardId;

    ProposalResponse ( Proposal proposal ) {
        this.proposalId = proposal.getId();
        this.document = proposal.getDocument();
        this.name = proposal.getName();
        this.email = proposal.getEmail();
        this.address = proposal.getAddress();
        this.salary = proposal.getSalary();
        this.status = proposal.getStatus().toString();
        this.cardId = proposal.getCard().getCardNumber();
    }

    public Long getProposalId () {
        return proposalId;
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

    public String getStatus () {
        return status;
    }

    public String getCardId () {
        return cardId;
    }
}