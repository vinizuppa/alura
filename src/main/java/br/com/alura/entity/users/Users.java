package br.com.alura.entity.users;

import br.com.alura.enums.RoleEnum;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.ZoneId;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String password;
    private RoleEnum role;
    private LocalDate creation_date;

    @PrePersist
    private void prePersist() {
        creation_date = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
    }
}
