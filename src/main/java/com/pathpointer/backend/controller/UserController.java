package com.pathpointer.backend.controller;

import com.pathpointer.backend.model.User;
import com.pathpointer.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/saveProfile")
    public User saveProfile(@RequestBody User user) {

        User existing = userRepository.findByEmail(user.getEmail()).orElse(null);

        if(existing != null){

            existing.setName(user.getName());
            existing.setLeetcodeId(user.getLeetcodeId());
            existing.setLanguage(user.getLanguage());
            existing.setTopics(user.getTopics());

            return userRepository.save(existing);
        }

        return userRepository.save(user);
    }
    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam String email){

        User user = userRepository.findByEmail(email).orElse(null);

        if(user == null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(user);
    }
}