package com.micro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Override
    public UserDTO userRegister(UserRegisterDTO userRegisterData) {
        if (!userRegisterData.getPassword().equals(userRegisterData.getPasswordConfirm())) {
            throw new AuthException("Пароли не совпадают");
        }

        if (userRepository.findByUsername(userRegisterData.getUsername()).isPresent()) {
            throw new AuthException("Пользователь уже существует");
        }
        System.out.println("----------------------");

        User user = User.builder()
                .username(userRegisterData.getUsername())
                .password(passwordEncoder.encode(userRegisterData.getPassword()))
                .roles(List.of(roleRepository.findByName("ROLE_USER").get()))
                .build();

        System.out.println("+++++++++++++++++++++++=");

        userRepository.save(user);

        return new UserDTO(user.getUsername());
    }


    @Override
    public LoginDTO userLogin(LoginDTO loginData) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginData.getUsername(),
                loginData.getPassword()

            ));
        } catch (BadCredentialsException exception) {
            throw new AuthException("Не правильный логин или пароль");
        }

        UserDetails userDetails = userService.loadUserByUsername(loginData.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);

        return LoginDTO.builder()
                .token(token)
                .build();
    }
}
