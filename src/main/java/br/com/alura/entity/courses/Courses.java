package br.com.alura.entity.courses;

import br.com.alura.entity.users.Users;
import br.com.alura.enums.StatusEnum;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;

@Entity
@Table(name = "courses")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "code", length = 10, nullable = false, unique = true)
    private String code;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Users instructor;

    @Column(name = "status", length = 15, nullable = false)
    private StatusEnum status;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "inactivation_date", nullable = true)
    private LocalDate inactivationDate;

    @PrePersist
    private void prePersist() {
        creationDate = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        status = StatusEnum.ACTIVE;
    }
}
