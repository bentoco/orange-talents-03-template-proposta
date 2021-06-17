package br.com.zup.proposal.proposal;

import br.com.zup.proposal.card.Card;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.*;

@Entity
@Table ( name = "proposal" )
public class Proposal {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

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

    @Enumerated ( EnumType.STRING )
    private ProposalState status;

    @OneToOne ( cascade = CascadeType.ALL )
    @JoinColumn ( name = "card_id", unique = true )
    private Card card;

    @Deprecated
    public Proposal () {
    }

    public Proposal ( String document , String name , String email , String address , BigDecimal salary ) {
        this.document = encoder.encode(document);
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

    public Card getCard () {
        return card;
    }

    public void setStatus ( String status ) {
        Assert.notNull(status , "critical error: status may not null");
        if (status.equals("COM_RESTRICAO")) this.status = ProposalState.NAO_ELEGIVEL;
        if (status.equals("SEM_RESTRICAO")) this.status = ProposalState.ELEGIVEL;
    }

    public void setCard ( Card card ) {
        this.card = card;
    }

    public static String encode(String rawData){
        return encoder.encode(rawData);
    }

}
