package com.meran.backendlicenta.controllers;

import com.meran.backendlicenta.models.User;
import com.meran.backendlicenta.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<User> signup(@RequestBody User user) {
        User existingUser = userRepository.findByName(user.getName());

        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User existingUser = userRepository.findByName(user.getName());

        if (existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(existingUser);
    }

    @GetMapping("/getCurrentUser")
    public ResponseEntity<User> getCurrentUser(@RequestParam Long id) {
        User existingUser = userRepository.findByUserId(id);
        if(existingUser == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(existingUser);
    }

//    @GetMapping("/getUserByProjectId")
//    public ResponseEntity<List<User>> getAllUsers(@RequestParam Long id) {
//        List<User> users = userRepository.findAllUsersByProjectId(id);
//        if(users == null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.ok(users);
//    }

}
