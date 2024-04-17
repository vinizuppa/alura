package br.com.alura.service.courses;

import br.com.alura.dto.courses.CourseRegisterDTO;
import br.com.alura.entity.courses.Courses;
import br.com.alura.entity.users.Users;
import br.com.alura.enums.RoleEnum;
import br.com.alura.enums.StatusEnum;
import br.com.alura.factory.courses.CoursesFactory;
import br.com.alura.repository.courses.CoursesRepository;
import br.com.alura.service.users.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CoursesService {
    private final CoursesRepository coursesRepository;
    private final UsersService usersService;

    public CoursesService(CoursesRepository coursesRepository, UsersService usersService) {
        this.coursesRepository = coursesRepository;
        this.usersService = usersService;
    }

    public void registerCourse(CourseRegisterDTO courseRegisterDTO) {
        try {
            var instructor = usersService.findUserById(courseRegisterDTO.instructor_id());
            this.validateUserIsInstructor(instructor);

            var courseEntity = CoursesFactory.createCoursesEntity(courseRegisterDTO, instructor);
            coursesRepository.save(courseEntity);
        } catch (Exception e) {
            //TODO fix exception
            throw e;
        }
    }

    private void validateUserIsInstructor(Users users) {
        if (users.getRole() != RoleEnum.INSTRUCTOR) {
            //TODO fix exception
            throw new RuntimeException("User is not Instructor");
        }
    }

    public void deactivateCourse(String courseCode) {
        try {
            var courseEntity = this.findCourseByCode(courseCode);
            courseEntity.setStatus(StatusEnum.INACTIVE);
            coursesRepository.save(courseEntity);
        } catch (Exception e) {
            //TODO fix exception
            throw e;
        }
    }

    private Courses findCourseByCode(String courseCode) {
        var courseEntity = coursesRepository.findCoursesByCode(courseCode);

        if (courseEntity == null) {
            //TODO fix exception
            throw new RuntimeException("Course not found");
        }

        return courseEntity;
    }

    public Page<Courses> getAllCoursesByStatus(Pageable pageable, StatusEnum status) {
        var coursesPage = coursesRepository.findCoursesByStatus(status, pageable);

        return coursesPage;
    }

    public Courses findCoursesById(Integer id) {
        var courseEntity = coursesRepository.findById(id);

        if (courseEntity.isEmpty()) {
            //TODO fix exception
            throw new RuntimeException("Course not found");
        }

        return courseEntity.get();
    }
}
