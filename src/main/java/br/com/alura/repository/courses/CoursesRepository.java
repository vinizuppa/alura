package br.com.alura.repository.courses;

import br.com.alura.entity.courses.Courses;
import br.com.alura.enums.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
    Courses findCoursesByCode(String code);
    @Query("SELECT c FROM Courses c WHERE (:status IS NULL OR c.status = :status)")
    Page<Courses> findCoursesByStatus(@Param("status") StatusEnum status, Pageable pageable);
}
