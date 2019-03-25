package br.com.randomthings.dto.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ClientPasswordEqualsValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientPasswordEquals {
 String message() default "Senhas diferentes";
 Class<?>[] groups() default {};
 Class<? extends Payload>[] payload() default {};
}