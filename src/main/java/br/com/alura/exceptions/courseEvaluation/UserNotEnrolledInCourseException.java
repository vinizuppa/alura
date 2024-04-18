package br.com.alura.exceptions.courseEvaluation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotEnrolledInCourseException extends RuntimeException {
    public UserNotEnrolledInCourseException(String message) {
        super(message);
    }
}
