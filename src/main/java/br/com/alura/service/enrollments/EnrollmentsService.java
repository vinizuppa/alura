package br.com.alura.service.enrollments;

import br.com.alura.dto.enrollments.EnrollmentRegisterDTO;
import br.com.alura.entity.courses.Courses;
import br.com.alura.enums.StatusEnum;
import br.com.alura.exceptions.enrollments.InactiveCourseException;
import br.com.alura.exceptions.enrollments.UserAlreadyEnrolledInCourseException;
import br.com.alura.factory.enrollments.EnrollmentsFactory;
import br.com.alura.repository.enrollments.EnrollmentsRepository;
import br.com.alura.service.courses.CoursesService;
import br.com.alura.service.users.UsersService;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentsService {
    private final EnrollmentsRepository enrollmentsRepository;
    private final UsersService usersService;
    private final CoursesService coursesService;

    public EnrollmentsService(EnrollmentsRepository enrollmentsRepository,
                              UsersService usersService,
                              CoursesService coursesService) {
        this.enrollmentsRepository = enrollmentsRepository;
        this.usersService = usersService;
        this.coursesService = coursesService;
    }

    public void registerEnrollment(EnrollmentRegisterDTO enrollmentRegisterDTO) {
        try {
            var userEntity = usersService.findUserById(enrollmentRegisterDTO.user_id());
            var courseEntity = coursesService.findCoursesById(enrollmentRegisterDTO.course_id());

            this.validateUserIsEnrolled(userEntity.getId(), courseEntity.getId());
            this.validateCourseIsInactive(courseEntity);

            var enrollmentEntity = EnrollmentsFactory.createEnrollmentsEntity(userEntity, courseEntity);
            enrollmentsRepository.save(enrollmentEntity);
        } catch (Exception e) {
            throw e;
        }
    }

    private void validateUserIsEnrolled(Integer userId, Integer courseId) {
        var enrollment = enrollmentsRepository.findEnrollmentByCoursesIdAndUsersId(userId, courseId);

        if (enrollment != null) {
            throw new UserAlreadyEnrolledInCourseException("User is already enrolled");
        }
    }

    private void validateCourseIsInactive(Courses courses) {
        if (courses.getStatus().equals(StatusEnum.INACTIVE)) {
            throw new InactiveCourseException("Course is inactive");
        }
    }
}
