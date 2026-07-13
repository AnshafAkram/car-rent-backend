package org.example.service;


import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.AuthResponse;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class AuthService {


    private final UserRepository userRepository;


    public AuthService(UserRepository userRepository) {

        this.userRepository = userRepository;

    }



    // Register

    public AuthResponse register(RegisterRequest request) {


        if(userRepository.existsByEmail(request.getEmail())) {

            throw new RuntimeException("Email already exists");

        }


        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setPassword(request.getPassword());

        user.setRole("CUSTOMER");


        User savedUser = userRepository.save(user);


        return new AuthResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );

    }



    // Login

    public AuthResponse login(LoginRequest request) {


        User user = userRepository.findByEmail(request.getEmail());


        if(user == null) {

            throw new RuntimeException("User not found");

        }


        if(!user.getPassword().equals(request.getPassword())) {

            throw new RuntimeException("Invalid password");

        }


        return new AuthResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

    }

}