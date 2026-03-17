package com.pathpointer.backend.repository;

import com.pathpointer.backend.entity.UserProfile;
import com.pathpointer.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUser(User user);
}