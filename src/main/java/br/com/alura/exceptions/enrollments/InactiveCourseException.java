package br.com.alura.exceptions.enrollments;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InactiveCourseException extends RuntimeException {
    public InactiveCourseException(String message) {
        super(message);
    }
}
