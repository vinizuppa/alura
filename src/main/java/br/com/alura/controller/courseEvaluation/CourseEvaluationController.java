package br.com.alura.controller.courseEvaluation;

import br.com.alura.dto.courseEvaluation.CourseEvaluationRegisterDTO;
import br.com.alura.dto.enrollments.EnrollmentRegisterDTO;
import br.com.alura.service.courseEvaluation.CourseEvaluationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course-evaluation")
public class CourseEvaluationController {
    private final CourseEvaluationService courseEvaluationService;

    public CourseEvaluationController(CourseEvaluationService courseEvaluationService) {
        this.courseEvaluationService = courseEvaluationService;
    }

    @PostMapping()
    public ResponseEntity<?> registerCourseEvaluation(@Valid @RequestBody CourseEvaluationRegisterDTO courseEvaluationRegisterDTO) {
        courseEvaluationService.registerCourseEvaluation(courseEvaluationRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("TODO");
    }
}