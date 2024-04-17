package br.com.alura.controller.enrollments;

import br.com.alura.dto.courses.CourseRegisterDTO;
import br.com.alura.dto.enrollments.EnrollmentRegisterDTO;
import br.com.alura.service.enrollments.EnrollmentsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentsController {
    private final EnrollmentsService enrollmentsService;

    public EnrollmentsController(EnrollmentsService enrollmentsService) {
        this.enrollmentsService = enrollmentsService;
    }

    @PostMapping()
    public ResponseEntity<?> registerEnrollment(@Valid @RequestBody EnrollmentRegisterDTO courseRegisterDTO) {
        enrollmentsService.registerEnrollment(courseRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("TODO");
    }
}
