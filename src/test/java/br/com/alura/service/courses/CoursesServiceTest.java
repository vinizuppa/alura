package br.com.alura.service.courses;

import br.com.alura.dto.courses.v1.CourseRegisterDTO;
import br.com.alura.entity.courses.Courses;
import br.com.alura.entity.users.Users;
import br.com.alura.enums.RoleEnum;
import br.com.alura.enums.StatusEnum;
import br.com.alura.repository.courses.CoursesRepository;
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
class CoursesServiceTest {
    @InjectMocks
    private CoursesService coursesService;
    @Mock
    private CoursesRepository coursesRepository;
    @Mock
    private UsersService usersService;

    private Users userRoleStudent;
    private Users userRoleInstructor;
    private CourseRegisterDTO courseRegisterDTO;
    private Courses courseEntity;
    @BeforeEach
    void setUp() {
        this.populateUserRoleStudent();
        this.populateUserRoleInstructor();
        this.populateCourseRegisterDTO();
        this.populateCourseEntity();
    }

    @Test
    public void shouldThrowAnExceptionWhenAttemptingToRegisterCourseWithUserResponsibleOfIncorrectRole() {
        Mockito.when(usersService.findUserById(ArgumentMatchers.any())).thenReturn(userRoleStudent);

        var courseRegisterDTO = new CourseRegisterDTO(null, null, null, null);

        try {
            coursesService.registerCourse(courseRegisterDTO);
        } catch (Exception e) {
            Assertions.assertEquals("User responsible for the course must be an instructor", e.getMessage());
        }
    }

    @Test
    public void shouldRegisterCourse() {
        Mockito.when(usersService.findUserById(ArgumentMatchers.any())).thenReturn(userRoleInstructor);
        coursesService.registerCourse(courseRegisterDTO);
        Mockito.verify(coursesRepository).save(ArgumentMatchers.any());
    }

    @Test
    public void shouldThrowAnExceptionWhenTryingToDeactivateCourseNotFound() {
        try {
            coursesService.deactivateCourse("test");
        } catch (Exception e) {
            Assertions.assertEquals("Course not found", e.getMessage());
        }
    }

    @Test
    public void shouldDeactivateCourse() {
        Mockito.when(coursesRepository.findCoursesByCode(ArgumentMatchers.any())).thenReturn(courseEntity);
        coursesService.deactivateCourse("test");
        Mockito.verify(coursesRepository).save(ArgumentMatchers.any());
    }

    public void populateUserRoleStudent() {
        userRoleStudent = new Users();
        userRoleStudent.setCreationDate(LocalDate.now());
        userRoleStudent.setId(1);
        userRoleStudent.setRole(RoleEnum.STUDENT);
    }

    public void populateUserRoleInstructor() {
        userRoleInstructor = new Users();
        userRoleInstructor.setCreationDate(LocalDate.now());
        userRoleInstructor.setId(1);
        userRoleInstructor.setRole(RoleEnum.INSTRUCTOR);
    }

    public void populateCourseRegisterDTO() {
        courseRegisterDTO = new CourseRegisterDTO("Test", "tst-junit", 1, "Teste");
    }

    public void populateCourseEntity() {
        courseEntity = new Courses();
        courseEntity.setId(1);
        courseEntity.setStatus(StatusEnum.ACTIVE);
    }
}