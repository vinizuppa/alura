package br.com.alura.service.users;

import br.com.alura.dto.users.v1.UserRegisterDTO;
import br.com.alura.dto.users.v1.UserSimpleResponseDTO;
import br.com.alura.entity.users.Users;
import br.com.alura.exceptions.users.UserNotFoundException;
import br.com.alura.factory.users.UserDTOFactory;
import br.com.alura.factory.users.UsersFactory;
import br.com.alura.repository.users.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void registerUser(UserRegisterDTO userRegisterDTO) {
        var userEntity = UsersFactory.createUserEntity(userRegisterDTO);

        usersRepository.save(userEntity);
    }

    public UserSimpleResponseDTO findUserByUsername(String username) {
        var userEntity = usersRepository.findUserByUsername(username);

        if (userEntity == null) {
            throw new UserNotFoundException("User not found");
        }
        var userSimpleResponseDTO = UserDTOFactory.createUserSimpleResponseDTO(userEntity);

        return userSimpleResponseDTO;
    }

    public Users findUserById(Integer id) {
        var userEntity = usersRepository.findById(id);

        if (userEntity.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        return userEntity.get();
    }

    public UserDetails findUserDetailsByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
