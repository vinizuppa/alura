package br.com.alura.controller.courses;

import br.com.alura.dto.courses.CourseRegisterDTO;
import br.com.alura.service.courses.CoursesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CoursesController {
    private final CoursesService coursesService;

    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @PostMapping()
    public ResponseEntity<?> registerCourse(@Valid @RequestBody CourseRegisterDTO courseRegisterDTO) {
        coursesService.registerCourse(courseRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("TODO");
    }
}
