package br.com.zup.proposal.card.travel;

import br.com.zup.proposal.card.Card;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class TravelNotice {

    @Id
    @GeneratedValue( generator = "UUID" )
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator" )
    private String id;

    @ManyToOne
    private Card card;

    @Column(nullable = false)
    private String destiny;

    @Column(nullable = false)
    private LocalDate travelEndTime;

    @Column(nullable = false)
    private String ipClient;

    @Column(nullable = false)
    private String userAgent;

    @CreationTimestamp
    private LocalDateTime noticeInstant;

    public TravelNotice(Card card, String destiny, LocalDate travelEndTime, String ipClient, String userAgent) {
        this.card = card;
        this.destiny = destiny;
        this.travelEndTime = travelEndTime;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
    }

    /* hibernate eyes only */
    @Deprecated
    public TravelNotice() {
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
