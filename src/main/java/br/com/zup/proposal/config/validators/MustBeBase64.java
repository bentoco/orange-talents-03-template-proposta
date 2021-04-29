package br.com.zup.proposal.config.validators;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target ( {FIELD} )
@Retention ( RUNTIME )
@Constraint ( validatedBy = MustBeBase64Validator.class )
public @interface MustBeBase64 {
    String message () default "must be a base64 valid";

    Class<?>[] groups () default {};

    Class<? extends Payload>[] payload () default {};

    Class<?> klazz ();

    String field ();
}

