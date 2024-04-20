package br.com.alura.controller.nps;

import br.com.alura.service.nps.NpsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nps")
public class NpsController {
    private final NpsService npsService;

    public NpsController(NpsService npsService) {
        this.npsService = npsService;
    }

    @Operation(description = "This endpoint returns a report with the nps of each course with more than 4 enrollments")
    @GetMapping("/report")
    public ResponseEntity<?> calculateNpsOfCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(npsService.calculateNpsOfCourses());
    }
}
