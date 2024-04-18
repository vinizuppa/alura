package br.com.alura.dto.courseEvaluation;

import org.hibernate.validator.constraints.Range;

public record CourseEvaluationRegisterDTO(Integer courseId,
                                          @Range(min = 0, max = 10, message = "Score should be between 0 and 10")
                                          Integer score,
                                          String reason) {
}
