package br.com.alura.controller.users;

import br.com.alura.dto.users.UserRegisterDTO;
import br.com.alura.dto.users.UserSimpleResponseDTO;
import br.com.alura.service.users.UsersService;
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

    @PostMapping()
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        usersService.registerUser(userRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("TODO");
    }

    @GetMapping()
    public ResponseEntity<?> findUserByUsername(@RequestParam String username) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.findUserByUsername(username));
    }
}
