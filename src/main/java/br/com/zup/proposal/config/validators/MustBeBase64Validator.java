package br.com.zup.proposal.config.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;

public class MustBeBase64Validator implements ConstraintValidator<MustBeBase64, String> {

    private Class<?> klazz;
    private String field;

    @Override public void initialize ( MustBeBase64 params ) {
        this.klazz = params.klazz();
        this.field = params.field();
    }

    @Override
    public boolean isValid ( String s , ConstraintValidatorContext constraintValidatorContext ) {
        try {
            Base64.getDecoder().decode(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
