package com.rabobank.userservice.domain.entities;

import com.rabobank.userservice.domain.dtos.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Uzer {

    @Id
    private String email;
    private String password;

    public static Uzer fromDto(UserDto userDto){
        Uzer uzer =  new Uzer();
        uzer.setEmail(userDto.email());
        uzer.setPassword(userDto.password());
        return uzer;
    }

}
