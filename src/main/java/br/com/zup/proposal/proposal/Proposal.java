package br.com.zup.proposal.proposal;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.math.BigDecimal;

import static javax.persistence.GenerationType.*;

@Entity
@Table ( name = "proposal" )
public class Proposal {

    @Id
    @GeneratedValue ( strategy = IDENTITY )
    @Column ( name = "proposal_id" )
    private Long id;

    @Column ( nullable = false )
    private String document;

    @Column ( nullable = false )
    private String name;

    @Column ( nullable = false )
    private String email;

    @Column ( nullable = false )
    private String address;

    @Column ( nullable = false )
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private ProposalState status;

    @Deprecated
    public Proposal () {
    }

    public Proposal ( String document , String name , String email , String address , BigDecimal salary ) {
        this.document = document;
        this.name = name;
        this.email = email;
        this.address = address;
        this.salary = salary;
    }

    public Long getId () {
        return id;
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

    public ProposalState getStatus () {
        return status;
    }

    public void setStatus ( String status ) {
        Assert.notNull(status, "critical error: status may not null");
        if(status.equals("COM_RESTRICAO")) this.status = ProposalState.NAO_ELEGIVEL;
        if(status.equals("SEM_RESTRICAO")) this.status = ProposalState.ELEGIVEL;
    }
}
