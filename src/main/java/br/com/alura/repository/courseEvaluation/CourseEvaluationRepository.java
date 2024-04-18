package br.com.alura.repository.courseEvaluation;

import br.com.alura.entity.courseEvaluation.CourseEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseEvaluationRepository extends JpaRepository<CourseEvaluation, Integer> {
    CourseEvaluation findCourseEvaluationByUsersIdAndCoursesId(Integer userId, Integer courseId);
}
