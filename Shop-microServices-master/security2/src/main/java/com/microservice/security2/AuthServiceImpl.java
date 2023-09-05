package com.microservice.security2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private SecurityClient securityClient;

    @Override
    public UserRegisterDTO userRegister(UserRegisterDTO registerData) {

        if (!registerData.getPassword().equals(registerData.getPasswordConfirm())) {
            throw new AuthException("Пароли не совпадают!");
        }

        if (userService.findByUsername(registerData.getUsername()).isPresent()) {
            throw new AuthException("Пользователь с таким никнеймом уже существует");
        }

        User user = new User(
                registerData.getUsername(),
                passwordEncoder.encode(registerData.getPassword())
        );

        user.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
        userRepository.save(user);

        log.info("Новый пользователь {}", user.getUsername());
        return registerData;
    }

    @Override
    public LoginDTO userLogin(LoginDTO loginData) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginData.getUsername(),
                    loginData.getPassword()
            ));
        } catch (BadCredentialsException e) {
            log.error("Неверные данные {} {}", loginData.getUsername(), loginData.getPassword());
            throw new AuthException("Неверный логин или пароль");
        }

        UserDetails userDetails = userService.loadUserByUsername(loginData.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);

        log.info("Пользователь аутенфицировался {}", loginData.getUsername());
        return new LoginDTO(token);
    }

    @Override
    public void getUserById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new AuthNotFoundException("пользователь не найден"));
    }

    @Override
    public void deleteUser(Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                        .orElseThrow(() -> new AuthNotFoundException("пользователь не найден"));

        securityClient.deleteUserCarts(user.getId());
        userRepository.deleteById(user.getId());

        log.info("Пользователь {} --> и его корзины удалены", user.getUsername());
    }
}
