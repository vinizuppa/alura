package br.com.alura.dto.courses.v1;

import br.com.alura.validator.users.CodeValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CourseRegisterDTO(@NotBlank
                                @Size(max = 255)
                                String name,
                                @CodeValidator
                                String code,
                                @NotNull
                                Integer instructor_id,
                                @NotBlank
                                @Size(max = 255)
                                String description) {
}
