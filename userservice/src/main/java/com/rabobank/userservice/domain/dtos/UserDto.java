package com.rabobank.userservice.domain.dtos;

import com.rabobank.userservice.domain.entities.Uzer;

public record UserDto (String email, String password){

    public static UserDto fromEntity(Uzer uzer) {
       return new UserDto(uzer.getEmail(), uzer.getPassword());
    }
}
