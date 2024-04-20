package br.com.alura.repository.courseEvaluation;

import br.com.alura.entity.courseEvaluation.CourseEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseEvaluationRepository extends JpaRepository<CourseEvaluation, Integer> {
    CourseEvaluation findCourseEvaluationByUsersIdAndCoursesId(Integer userId, Integer courseId);
    @Query("SELECT ce FROM CourseEvaluation ce " +
           "WHERE ce.courses.id IN ( " +
           "SELECT en.courses.id FROM Enrollments en GROUP BY en.courses.id HAVING COUNT(*) > 4)")
    List<CourseEvaluation> getAllCourseEvaluationsForNpsCalculator();
}
