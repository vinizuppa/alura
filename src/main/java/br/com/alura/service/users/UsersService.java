package br.com.alura.service.users;

import br.com.alura.dto.users.UserRegisterDTO;
import br.com.alura.dto.users.UserSimpleResponseDTO;
import br.com.alura.entity.users.Users;
import br.com.alura.factory.users.UserDTOFactory;
import br.com.alura.factory.users.UsersFactory;
import br.com.alura.repository.users.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void registerUser(UserRegisterDTO userRegisterDTO) {
        //TODO Exceptions
        var userEntity = UsersFactory.createUserEntity(userRegisterDTO);

        usersRepository.save(userEntity);
    }

    public UserSimpleResponseDTO findUserByUsername(String username) {
        //TODO Exceptions
        var userEntity = usersRepository.findUserByUsername(username);

        if (userEntity == null) {
            throw new RuntimeException("Error");
        }
        var userSimpleResponseDTO = UserDTOFactory.createUserSimpleResponseDTO(userEntity);

        return userSimpleResponseDTO;
    }

    public Users findUserById(Integer id) {
        var userEntity = usersRepository.findById(id);

        if (userEntity.isEmpty()) {
            //TODO fix exception
            throw new RuntimeException("User not found");
        }

        return userEntity.get();
    }
}
