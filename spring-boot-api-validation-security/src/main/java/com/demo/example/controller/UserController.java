package com.demo.example.controller;

import com.demo.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.demo.example.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    List<User> findAllUsers(){
       return userService.findAllUsers();
    }

    @GetMapping("/users/{userId}")
    ResponseEntity<User> findByUserId(@PathVariable("userId") @Min(1) int userId){
        return userService.findByUserId(userId);
    }

    @PostMapping("/users")
    ResponseEntity<User> createUser(@RequestBody @Valid User user){
        return userService.createUser(user);
    }

    @PutMapping("/users/{userId}")
    ResponseEntity<User> updateUser(@RequestBody @Valid User user, @PathVariable("userId") @Min(1) int userId){
        return userService.updateUser(user, userId);
    }

    @DeleteMapping("/users/{userId}")
    ResponseEntity<String> deleteUserById(@PathVariable @Min(1) int userId){
        return userService.deleteUserById(userId);
    }

}
