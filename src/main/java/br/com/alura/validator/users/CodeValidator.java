package br.com.alura.validator.users;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidCode.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeValidator {
    String message() default "Invalid code";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
