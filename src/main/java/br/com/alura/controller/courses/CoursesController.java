package br.com.alura.controller.courses;

import br.com.alura.dto.courses.CourseRegisterDTO;
import br.com.alura.enums.StatusEnum;
import br.com.alura.service.courses.CoursesService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping()
    public ResponseEntity<?> deactivateCourse(@RequestParam String courseCode) {
        coursesService.deactivateCourse(courseCode);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping()
    public ResponseEntity<?> getAllCoursesByStatus(Pageable pageable, @RequestParam(required = false) StatusEnum status) {
        return ResponseEntity.status(HttpStatus.OK).body(coursesService.getAllCoursesByStatus(pageable, status));
    }
}
