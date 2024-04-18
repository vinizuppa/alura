package br.com.alura.utils;

import br.com.alura.entity.users.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class FunctionUtils {
    public static String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static Users getLoggedUser() {
        return (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
