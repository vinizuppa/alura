package br.com.alura.dto.enrollments.v1;

import jakarta.validation.constraints.NotNull;

public record EnrollmentRegisterDTO(@NotNull
                                    Integer course_id) {
}
