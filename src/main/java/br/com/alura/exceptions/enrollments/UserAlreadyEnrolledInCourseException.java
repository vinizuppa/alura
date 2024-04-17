package br.com.alura.exceptions.enrollments;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyEnrolledInCourseException extends RuntimeException {
    public UserAlreadyEnrolledInCourseException(String message) {
        super(message);
    }
}
