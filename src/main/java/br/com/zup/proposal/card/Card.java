package br.com.zup.proposal.card;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table ( name = "card" )
public class Card {

    @Id
    @GeneratedValue ( generator = "UUID" )
    @GenericGenerator ( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator" )
    private String id;

    @Column ( nullable = false, unique = true, updatable = false )
    private String cardNumber;

    @OneToMany ( mappedBy = "card", cascade = CascadeType.ALL)
    private Set<Biometry> biometries = new HashSet<>();

    @Deprecated
    public Card () {
    }

    public Card ( String cardNumber ) {
        this.cardNumber = cardNumber;
    }

    public String getId () {
        return id;
    }

    public String getCardNumber () {
        return cardNumber;
    }

    public Set<Biometry> getBiometries () {
        return biometries;
    }

    public void addBiometry( Biometry biometry){
        biometry.setCard(this);
        biometries.add(biometry);
    }
}
