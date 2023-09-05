package com.rabobank.userservice.dtos;

import com.rabobank.userservice.entities.Uzer;

public record UserDto (String email, String password){

    public static UserDto fromEntity(Uzer uzer) {
       return new UserDto(uzer.getEmail(), uzer.getPassword());
    }
}
