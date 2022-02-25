package com.demo.example.service;

import com.demo.example.entity.User;
import com.demo.example.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.demo.example.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<User> findByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("User Id : %d is not found", userId)));
        return ResponseEntity.ok().body(user);
    }

    public ResponseEntity<User> createUser(User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    public ResponseEntity<User> updateUser(User userData, int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("User Id : %d is not found", userId)));
        user.setUserId(userId);
        return ResponseEntity.ok(userRepository.save(user));
    }

    public ResponseEntity<String> deleteUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("User Id : %d is not found", userId)));
        userRepository.deleteById(user.getUserId());
        return ResponseEntity.ok().body("User id: "+userId+" is deleted.");
    }
}
