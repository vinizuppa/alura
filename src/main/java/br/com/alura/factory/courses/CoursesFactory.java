package br.com.alura.factory.courses;

import br.com.alura.dto.courses.v1.CourseRegisterDTO;
import br.com.alura.entity.courses.Courses;
import br.com.alura.entity.users.Users;

public class CoursesFactory {
    public static Courses createCoursesEntity(CourseRegisterDTO courseRegisterDTO, Users instructor) {
        return Courses
                .builder()
                .name(courseRegisterDTO.name())
                .code(courseRegisterDTO.code())
                .instructor(instructor)
                .description(courseRegisterDTO.description())
                .build();
    }
}
