package br.com.zup.proposal.card.biometry;

import br.com.zup.proposal.card.Card;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "biometry")
public class Biometry {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(nullable = false, unique = true)
    private String fingerprint;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    private Card card;

    @Deprecated
    public Biometry () {
    }

    public Biometry ( String fingerprint ) {
        this.fingerprint = fingerprint;
    }

    public String getId () {
        return id;
    }

    public String getFingerprint () {
        return fingerprint;
    }

    public LocalDateTime getCreatedAt () {
        return createdAt;
    }

    public Card getCard () {
        return card;
    }

    public void setCard(Card card){
        this.card = card;
    }
}
