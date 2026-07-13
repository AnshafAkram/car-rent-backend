package org.example.controller;


import org.example.entity.User;
import org.example.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {


    private final AuthService authService;


    public AuthController(AuthService authService) {

        this.authService = authService;

    }



    // Register API
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {


        User savedUser = authService.register(user);


        return ResponseEntity.ok(savedUser);

    }



    // Login API
    @PostMapping("/login")
    public ResponseEntity<User> login(
            @RequestBody User user
    ) {


        User loggedUser = authService.login(
                user.getEmail(),
                user.getPassword()
        );


        return ResponseEntity.ok(loggedUser);

    }

}