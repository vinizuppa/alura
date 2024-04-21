package br.com.alura.service.courseEvaluation;

import br.com.alura.dto.courseEvaluation.v1.CourseEvaluationRegisterDTO;
import br.com.alura.entity.courseEvaluation.CourseEvaluation;
import br.com.alura.entity.courses.Courses;
import br.com.alura.entity.enrollments.Enrollments;
import br.com.alura.entity.users.Users;
import br.com.alura.enums.StatusEnum;
import br.com.alura.repository.courseEvaluation.CourseEvaluationRepository;
import br.com.alura.service.courses.CoursesService;
import br.com.alura.service.enrollments.EnrollmentsService;
import br.com.alura.service.users.UsersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;

@SpringBootTest
class CourseEvaluationServiceTest {
    @InjectMocks
    private CourseEvaluationService courseEvaluationService;
    @Mock
    private CourseEvaluationRepository courseEvaluationRepository;
    @Mock
    private UsersService usersService;
    @Mock
    private CoursesService coursesService;
    @Mock
    private EnrollmentsService enrollmentsService;

    private Courses courseEntity;
    private Users userEntity;
    private Enrollments enrollmentEntity;
    private CourseEvaluation courseEvaluationEntity;
    private CourseEvaluationRegisterDTO courseEvaluationRegisterDTO;
    @BeforeEach
    void setUp() {
        this.fillUserEntity();
        this.fillLoggedInUser();
        this.fillCourseEntity();
        this.fillEnrollmentEntity();
        this.fillCourseEvaluationEntity();
        this.fillCourseEvaluationRegisterDTO();
    }

    @Test
    public void shouldReturnAnExceptionIfTheUserNotEnrolledInTheCourse() {
        Mockito.when(coursesService.findCourseById(ArgumentMatchers.any())).thenReturn(courseEntity);
        Mockito.when(enrollmentsService.findEnrollmentByUserIdAndCourseId(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(null);

        try {
            courseEvaluationService.registerCourseEvaluation(courseEvaluationRegisterDTO);
        } catch (Exception e) {
            Assertions.assertEquals("User not enrolled in the course", e.getMessage());
        }
    }

    @Test
    public void shouldReturnAnExceptionIfTheUserHasAlreadyEvaluatedTheCourse() {
        Mockito.when(coursesService.findCourseById(ArgumentMatchers.any())).thenReturn(courseEntity);
        Mockito.when(enrollmentsService.findEnrollmentByUserIdAndCourseId(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(enrollmentEntity);
        Mockito.when(courseEvaluationRepository.findCourseEvaluationByUsersIdAndCoursesId(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(courseEvaluationEntity);

        try {
            courseEvaluationService.registerCourseEvaluation(courseEvaluationRegisterDTO);
        } catch (Exception e) {
            Assertions.assertEquals("User has already evaluated this course", e.getMessage());
        }
    }

    @Test
    public void shouldRegisterEvaluation() {
        Mockito.when(coursesService.findCourseById(ArgumentMatchers.any())).thenReturn(courseEntity);
        Mockito.when(enrollmentsService.findEnrollmentByUserIdAndCourseId(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(enrollmentEntity);
        Mockito.when(courseEvaluationRepository.findCourseEvaluationByUsersIdAndCoursesId(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(null);

        courseEvaluationService.registerCourseEvaluation(courseEvaluationRegisterDTO);

        Mockito.verify(courseEvaluationRepository).save(ArgumentMatchers.any());
    }

    private void fillUserEntity() {
        userEntity = new Users(1, "test", "test_user", "test@email.com", "pass", null, null);
    }

    private void fillLoggedInUser() {
        UserDetails user = userEntity;

        var authentication = new UsernamePasswordAuthenticationToken(user, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void fillCourseEntity() {
        courseEntity = new Courses(1, "test", "test", "descriptions test", userEntity, StatusEnum.ACTIVE, LocalDate.now(), null);
    }

    private void fillEnrollmentEntity() {
        enrollmentEntity = new Enrollments(1, userEntity, courseEntity, LocalDate.now());
    }

    private void fillCourseEvaluationEntity() {
        courseEvaluationEntity = new CourseEvaluation(1, userEntity, courseEntity, 6, "test", LocalDate.now());
    }

    private void fillCourseEvaluationRegisterDTO() {
        courseEvaluationRegisterDTO = new CourseEvaluationRegisterDTO(1, 6, "text");
    }
}