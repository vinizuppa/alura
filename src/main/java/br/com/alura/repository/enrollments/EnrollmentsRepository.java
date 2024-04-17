package br.com.alura.repository.enrollments;

import br.com.alura.entity.enrollments.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentsRepository extends JpaRepository<Enrollments, Integer> {
    Enrollments findEnrollmentByCoursesIdAndUsersId(Integer coursesId, Integer usersId);
}
