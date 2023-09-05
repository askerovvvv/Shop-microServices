package com.micro.security;

public interface AuthService {
    UserDTO userRegister(UserRegisterDTO userRegisterData);
    LoginDTO userLogin(LoginDTO loginData);
}
