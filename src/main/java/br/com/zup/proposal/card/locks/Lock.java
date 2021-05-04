package br.com.zup.proposal.card.locks;

import br.com.zup.proposal.card.Card;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "locks")
public class Lock {

    @Id
    @GeneratedValue ( generator = "UUID" )
    @GenericGenerator ( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator" )
    private String id;

    @ManyToOne
    private Card card;

    @CreationTimestamp
    private LocalDateTime blockedAt;

    @Column ( nullable = false )
    private String userAgent;

    @Column ( nullable = false )
    private String ipClient;

    @Deprecated
    public Lock () {
    }

    public Lock ( Card card , String userAgent , String ipClient ) {
        this.card = card;
        this.userAgent = userAgent;
        this.ipClient = ipClient;
    }

    public void setCard ( Card card ) {
        this.card = card;
    }

    public Card getCard () {
        return card;
    }

    public String getId () {
        return id;
    }

    public LocalDateTime getBlockedAt () {
        return blockedAt;
    }

    public String getUserAgent () {
        return userAgent;
    }

    public String getIpClient () {
        return ipClient;
    }
}
