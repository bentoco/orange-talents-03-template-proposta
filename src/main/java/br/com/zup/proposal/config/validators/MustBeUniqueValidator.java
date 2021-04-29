package br.com.zup.proposal.config.validators;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class MustBeUniqueValidator implements ConstraintValidator<MustBeUnique, Object> {

    private Class<?> klazz;
    private String field;

    @PersistenceContext
    private EntityManager manager;

    @Override public void initialize ( MustBeUnique params ) {
        this.klazz = params.klazz();
        this.field = params.field();
    }

    @Override public boolean isValid ( Object value , ConstraintValidatorContext context ) {
        String format = String.format("SELECT 1 FROM %s WHERE %s=:value" , klazz.getName() , field);
        Query query = manager.createQuery(format);
        query.setParameter("value" , value);
        List<?> result = query.getResultList();
        return result.isEmpty();
    }
}
