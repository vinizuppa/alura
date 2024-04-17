package br.com.alura.dto.users;

import br.com.alura.enums.RoleEnum;
import br.com.alura.validator.users.CodeValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegisterDTO(@NotBlank
                              String name,
                              @CodeValidator
                              String username,
                              @Email
                              String email,
                              @NotBlank
                              String password,
                              @NotNull
                              RoleEnum role) {
}
