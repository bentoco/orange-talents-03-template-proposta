package br.com.zup.proposal.card.wallet;

import br.com.zup.proposal.card.Card;
import br.com.zup.proposal.config.validators.MustBeUniqueWallet;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@MustBeUniqueWallet(klazz = DigitalWallet.class)
public class DigitalWalletRequest {

    @Email
    @NotBlank
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Wallet wallet;

    public DigitalWallet toDigitalWallet(Card card) {
        return new DigitalWallet(email, wallet, card);
    }

    public String getEmail() {
        return email;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
