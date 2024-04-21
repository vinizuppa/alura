package br.com.alura.controller.enrollments;

import br.com.alura.dto.enrollments.v1.EnrollmentRegisterDTO;
import br.com.alura.service.enrollments.EnrollmentsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/enrollment")
public class EnrollmentsController {
    private final EnrollmentsService enrollmentsService;

    public EnrollmentsController(EnrollmentsService enrollmentsService) {
        this.enrollmentsService = enrollmentsService;
    }

    @Operation(description = "This endpoint saves enrollment for course")
    @PostMapping()
    public ResponseEntity<?> registerEnrollment(@Valid @RequestBody EnrollmentRegisterDTO courseRegisterDTO) {
        enrollmentsService.registerEnrollment(courseRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
