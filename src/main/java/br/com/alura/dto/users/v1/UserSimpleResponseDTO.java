package br.com.alura.dto.users.v1;

import br.com.alura.enums.RoleEnum;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserSimpleResponseDTO {
    private String name;
    private String email;
    private RoleEnum role;
}
