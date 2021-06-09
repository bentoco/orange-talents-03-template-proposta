package br.com.zup.proposal.config.validators;

import br.com.zup.proposal.card.wallet.DigitalWalletRequest;
import br.com.zup.proposal.card.wallet.Wallet;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class MustBeUniqueWalletValidator implements ConstraintValidator<MustBeUniqueWallet, DigitalWalletRequest> {

    @PersistenceContext
    private EntityManager manager;

    private Class<?> klazz;

    @Override
    public void initialize(MustBeUniqueWallet params) {
        klazz = params.klazz();
    }

    @Override
    public boolean isValid(DigitalWalletRequest value, ConstraintValidatorContext context) {
        String emailRequest = value.getEmail();
        Wallet walletRequest = value.getWallet();

        Query query = manager.createQuery("SELECT 1 FROM " + klazz.getName() + " WHERE email=:emailRequest AND wallet=:walletRequest");
        query.setParameter("emailRequest", emailRequest);
        query.setParameter("walletRequest", walletRequest);

        List<?> result = query.getResultList();
        return result.isEmpty();
    }
}
