package br.com.alura.validator.users;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidCode implements ConstraintValidator<CodeValidator, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return this.validLength(value) && this.validPatternCode(value);
    }

    private boolean validLength(String value) {
        return value.length() <= 10 && value.length() > 0;
    }

    private boolean validPatternCode(String value) {
        String regex = "^[a-zA-Z\\-]*$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }
}
