package br.com.zup.proposal.card.wallet;

import br.com.zup.proposal.card.Card;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class DigitalWallet {

    @Id
    @GeneratedValue( generator = "UUID" )
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator" )
    private String id;

    private String email;

    private Wallet wallet;

    @ManyToOne
    private Card card;

    /* hibernate eyes only  */
    @Deprecated
    public DigitalWallet() {
    }

    public DigitalWallet(String email, Wallet wallet, Card card) {
        this.email = email;
        this.wallet = wallet;
        this.card = card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
