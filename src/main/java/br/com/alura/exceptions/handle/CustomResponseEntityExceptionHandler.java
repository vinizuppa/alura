package br.com.alura.exceptions.handle;

import br.com.alura.exceptions.ExceptionResponse;
import br.com.alura.exceptions.courseEvaluation.UserAlreadyEvaluatedCourseException;
import br.com.alura.exceptions.courseEvaluation.UserNotEnrolledInCourseException;
import br.com.alura.exceptions.courses.CourseNotFoundException;
import br.com.alura.exceptions.courses.InvalidUserException;
import br.com.alura.exceptions.enrollments.InactiveCourseException;
import br.com.alura.exceptions.enrollments.UserAlreadyEnrolledInCourseException;
import br.com.alura.exceptions.users.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({InactiveCourseException.class, UserAlreadyEnrolledInCourseException.class, UserAlreadyEvaluatedCourseException.class})
    public final ResponseEntity<ExceptionResponse> handleConflictExceptions(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({InvalidUserException.class})
    public final ResponseEntity<ExceptionResponse> handleUnprocessableEntityExceptions(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({CourseNotFoundException.class, UserNotFoundException.class, UserNotEnrolledInCourseException.class})
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
