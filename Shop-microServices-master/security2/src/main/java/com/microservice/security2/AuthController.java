package com.microservice.security2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/security")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterDTO> userRegister(@RequestBody UserRegisterDTO registerData) {
        return ResponseEntity.status(201).body(authService.userRegister(registerData));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> userLogin(@RequestBody LoginDTO loginData) {
        return ResponseEntity.status(200).body(authService.userLogin(loginData));
    }

    @GetMapping("/get-user/{user-id}")
    public ResponseEntity<?> getUserById(@PathVariable("user-id") Long userId) {
        authService.getUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUser(Principal principal) {
        authService.deleteUser(principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
