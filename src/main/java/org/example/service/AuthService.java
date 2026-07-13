package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class AuthService {


    private final UserRepository userRepository;


    public AuthService(UserRepository userRepository) {

        this.userRepository = userRepository;

    }


    // Register User
    public User register(User user) {


        if(userRepository.existsByEmail(user.getEmail())) {

            throw new RuntimeException("Email already exists");

        }


        if(user.getRole() == null || user.getRole().isEmpty()) {

            user.setRole("CUSTOMER");

        }


        return userRepository.save(user);

    }



    // Login User
    public User login(String email, String password) {


        User user = userRepository.findByEmail(email);


        if(user == null) {

            throw new RuntimeException("User not found");

        }


        if(!user.getPassword().equals(password)) {

            throw new RuntimeException("Invalid password");

        }


        return user;

    }

}