package br.com.alura.repository.users;

import br.com.alura.entity.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findUserByUsername(String username);
}
