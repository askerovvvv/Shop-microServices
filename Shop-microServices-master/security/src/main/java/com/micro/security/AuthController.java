package com.micro.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> userRegister(UserRegisterDTO registerData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.userRegister(registerData));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> userLogin(LoginDTO loginData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.userLogin(loginData));
    }

}
