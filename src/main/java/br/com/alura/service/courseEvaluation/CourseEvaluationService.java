package br.com.alura.service.courseEvaluation;

import br.com.alura.dto.courseEvaluation.CourseEvaluationRegisterDTO;
import br.com.alura.exceptions.courseEvaluation.UserNotEnrolledInCourseException;
import br.com.alura.exceptions.enrollments.UserAlreadyEnrolledInCourseException;
import br.com.alura.repository.courseEvaluation.CourseEvaluationRepository;
import br.com.alura.service.courses.CoursesService;
import br.com.alura.service.enrollments.EnrollmentsService;
import br.com.alura.service.users.UsersService;
import org.springframework.stereotype.Service;

@Service
public class CourseEvaluationService {
    private final CourseEvaluationRepository courseEvaluationRepository;
    private final UsersService usersService;
    private final CoursesService coursesService;
    private final EnrollmentsService enrollmentsService;

    public CourseEvaluationService(CourseEvaluationRepository courseEvaluationRepository,
                                   UsersService usersService,
                                   CoursesService coursesService,
                                   EnrollmentsService enrollmentsService) {
        this.courseEvaluationRepository = courseEvaluationRepository;
        this.usersService = usersService;
        this.coursesService = coursesService;
        this.enrollmentsService = enrollmentsService;
    }

    public void registerCourseEvaluation(CourseEvaluationRegisterDTO courseEvaluationRegisterDTO) {
        //TODO Capture logged-in user
//        var userEntity = usersService.findUserById();
        var courseEntity = coursesService.findCourseById(courseEvaluationRegisterDTO.courseId());

        //TODO fix userId
        this.validateUserEnrolledInTheCourse(0, courseEntity.getId());
        this.validateUserHasAlreadyEvaluated(0, courseEntity.getId());

    }

    private void validateUserHasAlreadyEvaluated(Integer userId, Integer courseId) {
        var courseEvaluation = courseEvaluationRepository.findCourseEvaluationByUsersIdAndCoursesId(userId, courseId);

        if (courseEvaluation != null) {
            throw new UserAlreadyEnrolledInCourseException("User has already evaluated this course");
        }
    }

    private void validateUserEnrolledInTheCourse(Integer userId, Integer courseId) {
        var enrollmentEntity =  enrollmentsService.findEnrollmentByUserIdAndCourseId(userId, courseId);

        if (enrollmentEntity == null) {
            throw new UserNotEnrolledInCourseException("User not enrolled in the course");
        }
    }
}
