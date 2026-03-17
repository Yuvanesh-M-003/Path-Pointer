package com.pathpointer.backend.service;

import com.pathpointer.backend.model.User;
import com.pathpointer.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User findOrCreateUser(String email) {

        User existingUser = repo.findByEmail(email).orElse(null);

        if (existingUser != null) {
            return existingUser;
        }

        User newUser = new User();
        newUser.setEmail(email);

        return repo.save(newUser);
    }
}