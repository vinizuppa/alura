package br.com.alura.entity.enrollments;

import br.com.alura.entity.courses.Courses;
import br.com.alura.entity.users.Users;
import br.com.alura.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.ZoneId;

@Entity
@Table(name = "enrollments")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Enrollments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses courses;

    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollmentDate;

    @PrePersist
    private void prePersist() {
        enrollmentDate = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
    }
}
