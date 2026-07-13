package org.example.controller;


import org.example.dto.request.LoginRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.AuthResponse;
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



    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request
    ){

        return ResponseEntity.ok(
                authService.register(request)
        );

    }



    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request
    ){

        return ResponseEntity.ok(
                authService.login(request)
        );

    }

}