package com.microservice.security2;

import java.security.Principal;

public interface AuthService {
    UserRegisterDTO userRegister(UserRegisterDTO registerData);
    LoginDTO userLogin(LoginDTO loginData);
    void getUserById(Long id);
    void deleteUser(Principal principal);
}
