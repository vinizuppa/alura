package br.com.alura.entity.courseEvaluation;

import br.com.alura.entity.courses.Courses;
import br.com.alura.entity.users.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.ZoneId;

@Entity
@Table(name = "course_evaluation")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses courses;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "reason", length = 255, nullable = false)
    private String reason;

    @Column(name = "evaluation_date", nullable = false)
    private LocalDate evaluationDate;

    @PrePersist
    private void prePersist() {
        evaluationDate = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
    }
}
