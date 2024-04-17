package br.com.alura.factory.users;

import br.com.alura.dto.users.UserRegisterDTO;
import br.com.alura.entity.users.Users;

public class UsersFactory {
    public static Users createUserEntity(UserRegisterDTO userRegisterDTO) {
        return Users
                .builder()
                .name(userRegisterDTO.name())
                .username(userRegisterDTO.username())
                .email(userRegisterDTO.email())
                .password(userRegisterDTO.password())
                .role(userRegisterDTO.role())
                .build();
    }
}
