package br.com.alura.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class FunctionUtils {
    public static String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
