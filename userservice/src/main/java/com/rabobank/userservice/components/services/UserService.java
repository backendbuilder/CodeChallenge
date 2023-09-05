package com.rabobank.userservice.components.services;

import com.rabobank.userservice.domain.dtos.UserDto;
import com.rabobank.userservice.domain.entities.Uzer;
import com.rabobank.userservice.domain.exceptions.NotFoundException;
import com.rabobank.userservice.components.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    /**
     * Retrieves a User from the database based on it' s email.
     * @param email of the User
     * @return a UserDto that holds all the neccessary info of the User entity
     * @throws NotFoundException if the email does not exist in the database
     */
    public UserDto getUser(String email) throws NotFoundException {
        return UserDto.fromEntity(userRepository.findByEmailIgnoreCase(email).orElseThrow(NotFoundException::new));
    }

    /**
     * Saves a User in the database.
     * @param userDto holds all the data to create a User entity
     * @return the saved User converted to a UserDto
     */
    public UserDto saveUser(UserDto userDto) {
        Uzer uzer = userRepository.save(Uzer.fromDto(userDto));
        return UserDto.fromEntity(uzer);
    }


}
