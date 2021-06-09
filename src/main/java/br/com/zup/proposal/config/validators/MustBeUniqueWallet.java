package br.com.zup.proposal.config.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({TYPE, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = MustBeUniqueWalletValidator.class)
public @interface MustBeUniqueWallet {
    String message() default "cannot add card to the same wallet";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> klazz();
}
