package br.com.alura.exceptions.courseEvaluation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyEvaluatedCourseException extends RuntimeException {
    public UserAlreadyEvaluatedCourseException(String message) {
        super(message);
    }
}
