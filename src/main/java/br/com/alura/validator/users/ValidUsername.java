package br.com.alura.validator.users;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidUsername implements ConstraintValidator<UsernameValidator, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return this.validLength(value) && this.validPatternUsername(value);
    }

    private boolean validLength(String value) {
        return value.length() <= 20 && value.length() > 0;
    }

    private boolean validPatternUsername(String value) {
        String regex = "^[a-z]*$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }
}
