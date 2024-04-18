package br.com.alura.dto.enrollments;

import jakarta.validation.constraints.NotNull;

public record EnrollmentRegisterDTO(@NotNull
                                    Integer course_id) {
}
