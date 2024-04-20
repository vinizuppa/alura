package br.com.alura.service.courseEvaluation;

import br.com.alura.dto.courseEvaluation.CourseEvaluationRegisterDTO;
import br.com.alura.entity.courseEvaluation.CourseEvaluation;
import br.com.alura.entity.emailSender.EmailSender;
import br.com.alura.exceptions.courseEvaluation.UserNotEnrolledInCourseException;
import br.com.alura.exceptions.enrollments.UserAlreadyEnrolledInCourseException;
import br.com.alura.factory.courseEvaluation.CourseEvaluationFactory;
import br.com.alura.repository.courseEvaluation.CourseEvaluationRepository;
import br.com.alura.service.courses.CoursesService;
import br.com.alura.service.enrollments.EnrollmentsService;
import br.com.alura.service.users.UsersService;
import br.com.alura.utils.FunctionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseEvaluationService {
    private final CourseEvaluationRepository courseEvaluationRepository;
    private final UsersService usersService;
    private final CoursesService coursesService;
    private final EnrollmentsService enrollmentsService;

    private final Integer DETRACTING_NOTE = 6;
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
        var userEntity = FunctionUtils.getLoggedUser();
        var courseEntity = coursesService.findCourseById(courseEvaluationRegisterDTO.courseId());

        this.validateUserEnrolledInTheCourse(userEntity.getId(), courseEntity.getId());
        this.validateUserHasAlreadyEvaluated(userEntity.getId(), courseEntity.getId());

        var courseEvalutionEntity = CourseEvaluationFactory.createCourseEvaluationEntity(courseEvaluationRegisterDTO, courseEntity, userEntity);

        courseEvaluationRepository.save(courseEvalutionEntity);
        this.sendEmailIfNecessary(courseEvalutionEntity);
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

    private void sendEmailIfNecessary(CourseEvaluation courseEvaluation) {
        if (courseEvaluation.getScore() < DETRACTING_NOTE) {
            EmailSender.send(courseEvaluation.getCourses().getInstructor().getEmail(),
                    "Detracting Evaluation for Course: " + courseEvaluation.getCourses().getName(),
                            "Score: " + courseEvaluation.getScore() + "|" + "Reason: " + courseEvaluation.getReason());
        }
    }

    public List<CourseEvaluation> getAllCourseEvaluationsForNpsCalculator() {
        return courseEvaluationRepository.getAllCourseEvaluationsForNpsCalculator();
    }
}
