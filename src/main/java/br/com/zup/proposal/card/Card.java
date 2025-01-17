package br.com.zup.proposal.card;

import br.com.zup.proposal.card.biometry.Biometry;
import br.com.zup.proposal.card.locks.Lock;
import br.com.zup.proposal.card.travel.TravelNotice;
import br.com.zup.proposal.card.wallet.DigitalWallet;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(nullable = false, unique = true, updatable = false)
    private String cardNumber;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Biometry> biometries = new ArrayList<>();

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Lock> locks = new ArrayList<>();

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<TravelNotice> travelNotices = new ArrayList<>();

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<DigitalWallet> digitalWallets = new ArrayList<>();

    @Deprecated
    public Card() {
    }

    public Card(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public List<Biometry> getBiometries() {
        return biometries;
    }

    public List<Lock> locks() {
        return locks;
    }

    public List<TravelNotice> getTravelNotices() {
        return travelNotices;
    }

    public List<DigitalWallet> getDigitalWallets() {
        return digitalWallets;
    }

    public void addTravelNotice(TravelNotice travelNotice) {
        travelNotice.setCard(this);
        travelNotices.add(travelNotice);
    }

    public void addBiometry(Biometry biometry) {
        biometry.setCard(this);
        biometries.add(biometry);
    }

    public void addLock(Lock lock) {
        lock.setCard(this);
        locks.add(lock);
    }

    public void addDigitalWallet(DigitalWallet digitalWallet) {
        digitalWallet.setCard(this);
        digitalWallets.add(digitalWallet);
    }
}
