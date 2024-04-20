package br.com.alura.entity.users;

import br.com.alura.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "username", length = 20, nullable = false, unique = true)
    private String username;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "role", length = 30, nullable = false)
    private RoleEnum role;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @PrePersist
    private void prePersist() {
        creationDate = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role != null) {
            switch (this.role) {
                case ADMIN:
                    return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
                case INSTRUCTOR:
                    return List.of(new SimpleGrantedAuthority("ROLE_INSTRUCTOR"));
                default:
                    return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
            }
        }

        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
