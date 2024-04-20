package br.com.alura.controller.courses;

import br.com.alura.dto.courses.CourseRegisterDTO;
import br.com.alura.enums.StatusEnum;
import br.com.alura.service.courses.CoursesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(description = "This endpoint saves new course")
    @PostMapping()
    public ResponseEntity<?> registerCourse(@Valid @RequestBody CourseRegisterDTO courseRegisterDTO) {
        coursesService.registerCourse(courseRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(description = "This endpoint inactive course")
    @PutMapping()
    public ResponseEntity<?> deactivateCourse(@RequestParam String courseCode) {
        coursesService.deactivateCourse(courseCode);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Parameters({
            @Parameter(in = ParameterIn.QUERY
                    , description = "Page you want to retrieve (0..N)"
                    , name = "page"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "Number of records per page."
                    , name = "size"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "10"))),
            @Parameter(name = "pageable"
                    ,hidden = true)
    })
    @Operation(description = "This endpoint searches all paginated courses")
    @GetMapping()
    public ResponseEntity<?> getAllCoursesByStatus(Pageable pageable, @RequestParam(required = false) StatusEnum status) {
        return ResponseEntity.status(HttpStatus.OK).body(coursesService.getAllCoursesByStatus(pageable, status));
    }
}
