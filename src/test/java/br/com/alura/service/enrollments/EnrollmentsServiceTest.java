package br.com.alura.service.enrollments;

import br.com.alura.dto.enrollments.EnrollmentRegisterDTO;
import br.com.alura.entity.courses.Courses;
import br.com.alura.entity.enrollments.Enrollments;
import br.com.alura.entity.users.Users;
import br.com.alura.enums.RoleEnum;
import br.com.alura.enums.StatusEnum;
import br.com.alura.repository.enrollments.EnrollmentsRepository;
import br.com.alura.service.courses.CoursesService;
import br.com.alura.service.users.UsersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class EnrollmentsServiceTest {
    @InjectMocks
    private EnrollmentsService enrollmentsService;
    @Mock
    private EnrollmentsRepository enrollmentsRepository;
    @Mock
    private UsersService usersService;
    @Mock
    private CoursesService coursesService;

    private Courses courseEntityInactive;
    private Courses courseEntityActive;
    private Users userEntity;
    private Enrollments enrollmentEntity;
    private EnrollmentRegisterDTO enrollmentRegisterDTO;
    @BeforeEach
    void setUp() {
        this.populateCourseEntityInactive();
        this.populateCourseEntityActive();
        this.populateUserRoleInstructor();
        this.populateEnrollment();
        this.populateEnrollmentRegisterDTO();
    }

    @Test
    public void shouldThrowExceptionWhenAttempingToRegisterEnrollmentWithUserAlreadyRegisteredInCourse(){
        Mockito.when(coursesService.findCourseById(ArgumentMatchers.any())).thenReturn(courseEntityInactive);
        Mockito.when(usersService.findUserById(ArgumentMatchers.any())).thenReturn(userEntity);
        Mockito.when(enrollmentsRepository.findEnrollmentByCoursesIdAndUsersId(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(enrollmentEntity);

        try {
            enrollmentsService.registerEnrollment(enrollmentRegisterDTO);
        } catch (Exception e) {
            Assertions.assertEquals("User is already enrolled", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenAttempingToRegisterEnrollmentWithCourseIsInactive(){
        Mockito.when(coursesService.findCourseById(ArgumentMatchers.any())).thenReturn(courseEntityInactive);
        Mockito.when(usersService.findUserById(ArgumentMatchers.any())).thenReturn(userEntity);
        Mockito.when(enrollmentsRepository.findEnrollmentByCoursesIdAndUsersId(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(null);

        try {
            enrollmentsService.registerEnrollment(enrollmentRegisterDTO);
        } catch (Exception e) {
            Assertions.assertEquals("Course is inactive", e.getMessage());
        }
    }

    @Test
    public void shouldRegisterEnrollment() {
        Mockito.when(coursesService.findCourseById(ArgumentMatchers.any())).thenReturn(courseEntityActive);
        Mockito.when(usersService.findUserById(ArgumentMatchers.any())).thenReturn(userEntity);
        Mockito.when(enrollmentsRepository.findEnrollmentByCoursesIdAndUsersId(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(null);

        enrollmentsService.registerEnrollment(enrollmentRegisterDTO);
        Mockito.verify(enrollmentsRepository).save(ArgumentMatchers.any());
    }
    public void populateCourseEntityInactive() {
        courseEntityInactive = new Courses();
        courseEntityInactive.setId(1);
        courseEntityInactive.setStatus(StatusEnum.INACTIVE);
    }
    public void populateCourseEntityActive() {
        courseEntityActive = new Courses();
        courseEntityActive.setId(1);
        courseEntityActive.setStatus(StatusEnum.ACTIVE);
    }

    public void populateUserRoleInstructor() {
        userEntity = new Users();
        userEntity.setCreationDate(LocalDate.now());
        userEntity.setId(1);
        userEntity.setRole(RoleEnum.INSTRUCTOR);
    }

    public void populateEnrollment() {
        enrollmentEntity = new Enrollments();
        enrollmentEntity.setId(1);
    }

    public void populateEnrollmentRegisterDTO() {
        enrollmentRegisterDTO = new EnrollmentRegisterDTO(1);
    }
}