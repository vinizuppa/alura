package br.com.alura.factory.users;

import br.com.alura.dto.users.v1.UserSimpleResponseDTO;
import br.com.alura.entity.users.Users;

public class UserDTOFactory {
    public static UserSimpleResponseDTO createUserSimpleResponseDTO(Users users) {
        return UserSimpleResponseDTO
                .builder()
                .name(users.getName())
                .email(users.getEmail())
                .role(users.getRole())
                .build();
    }
}
