package br.com.alura.factory.courseEvaluation;

import br.com.alura.dto.courseEvaluation.CourseEvaluationRegisterDTO;
import br.com.alura.entity.courseEvaluation.CourseEvaluation;
import br.com.alura.entity.courses.Courses;
import br.com.alura.entity.users.Users;

public class CourseEvaluationFactory {
    public static CourseEvaluation createCourseEvaluationEntity(CourseEvaluationRegisterDTO courseEvaluationRegisterDTO, Courses courses, Users users) {
        return CourseEvaluation
                .builder()
                .courses(courses)
                .users(users)
                .score(courseEvaluationRegisterDTO.score())
                .reason(courseEvaluationRegisterDTO.reason())
                .build();
    }
}
