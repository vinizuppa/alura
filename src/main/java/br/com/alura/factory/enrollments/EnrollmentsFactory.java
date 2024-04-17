package br.com.alura.factory.enrollments;

import br.com.alura.entity.courses.Courses;
import br.com.alura.entity.enrollments.Enrollments;
import br.com.alura.entity.users.Users;

public class EnrollmentsFactory {
    public static Enrollments createEnrollmentsEntity(Users users, Courses courses) {
        return Enrollments
                .builder()
                .courses(courses)
                .users(users)
                .build();
    }
}
