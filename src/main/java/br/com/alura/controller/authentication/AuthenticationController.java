package br.com.alura.controller.authentication;

import br.com.alura.dto.users.TokenResponseDTO;
import br.com.alura.dto.users.UserLoginDTO;
import br.com.alura.dto.users.UserRegisterDTO;
import br.com.alura.entity.users.Users;
import br.com.alura.infra.security.TokenService;
import br.com.alura.service.users.UsersService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UsersService usersService;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UsersService usersService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usersService = usersService;
        this.tokenService = tokenService;
    }

    @SecurityRequirements(value = {})
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserLoginDTO userLoginDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(userLoginDTO.username(), userLoginDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Users) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

    @SecurityRequirements(value = {})
    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UserRegisterDTO userRegisterDTO){
        if (usersService.findUserDetailsByUsername(userRegisterDTO.username()) != null) {
            return ResponseEntity.badRequest().build();
        }

        this.usersService.registerUser(userRegisterDTO);

        return ResponseEntity.ok().build();
    }
}
