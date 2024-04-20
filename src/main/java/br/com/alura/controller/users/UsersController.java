package br.com.alura.controller.users;

import br.com.alura.dto.users.UserRegisterDTO;
import br.com.alura.dto.users.UserSimpleResponseDTO;
import br.com.alura.service.users.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @Operation(description = "This endpoint searches for user by username")
    @GetMapping()
    public ResponseEntity<?> findUserByUsername(@RequestParam String username) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.findUserByUsername(username));
    }
}
