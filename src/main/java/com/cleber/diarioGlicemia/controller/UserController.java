package com.cleber.diarioGlicemia.controller;

import com.cleber.diarioGlicemia.config.TokenService;
import com.cleber.diarioGlicemia.controller.request.LoginRequest;
import com.cleber.diarioGlicemia.controller.request.UserRequest;
import com.cleber.diarioGlicemia.controller.response.LoginResponse;
import com.cleber.diarioGlicemia.controller.response.UserResponse;
import com.cleber.diarioGlicemia.entity.User;
import com.cleber.diarioGlicemia.exceptions.UserNameOuPasswordInvalidException;
import com.cleber.diarioGlicemia.mapper.Usermapper;
import com.cleber.diarioGlicemia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diarioGlicemia/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> users = userService.findAll()
                .stream()
                .map(user -> Usermapper.toUserResponse(user))
                .toList();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> ResponseEntity.ok(Usermapper.toUserResponse(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        User newUser = Usermapper.toUser(request);
        User savedUser = userService.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(Usermapper.toUserResponse(savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken userAndPass =new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authenticated = authenticationManager.authenticate(userAndPass);

            User user = (User) authenticated.getPrincipal();

            String token = tokenService.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(token));

        } catch (BadCredentialsException e) {
            throw new UserNameOuPasswordInvalidException("Usuário ou senha Inválido.");
        }
    }

    //TODO criar a rota de update

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
