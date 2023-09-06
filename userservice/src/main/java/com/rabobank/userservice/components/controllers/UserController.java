package com.rabobank.userservice.components.controllers;


import com.rabobank.userservice.domain.exceptions.NotFoundException;
import com.rabobank.userservice.components.services.UserService;
import com.rabobank.userservice.domain.dtos.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUser(@PathVariable String email) {
        try {
            return ResponseEntity.ok(userService.getUser(email));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping()
    public ResponseEntity<UserDto> putUser(@RequestBody UserDto userDto) {
        try{
            UserDto savedUserDto = userService.saveUser(userDto);
            return ResponseEntity.ok(savedUserDto);
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.unprocessableEntity().build();
        }
    }


}
