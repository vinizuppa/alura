package br.com.alura.service.courses;

import br.com.alura.dto.courses.CourseRegisterDTO;
import br.com.alura.entity.users.Users;
import br.com.alura.enums.RoleEnum;
import br.com.alura.factory.courses.CoursesFactory;
import br.com.alura.repository.courses.CoursesRepository;
import br.com.alura.service.users.UsersService;
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
}
