package br.com.alura.controller.users;

import br.com.alura.service.users.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user")
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
