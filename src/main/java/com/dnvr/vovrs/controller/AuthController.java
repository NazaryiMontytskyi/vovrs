package com.dnvr.vovrs.controller;

import com.dnvr.vovrs.dto.AuthRequest;
import com.dnvr.vovrs.dto.AuthResponse;
import com.dnvr.vovrs.dto.RegisterRequest;
import com.dnvr.vovrs.model.Role;
import com.dnvr.vovrs.model.User;
import com.dnvr.vovrs.repository.UserRepository;
import com.dnvr.vovrs.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password())
        );

        User user = userRepository.findByEmail(authRequest.email()).orElseThrow();
        Set<String> roles = user.getRoles().stream().map(Enum::name).collect(Collectors.toSet());
        String token = jwtUtil.generateToken(user.getEmail(), roles);
        long expiresAt = System.currentTimeMillis() + jwtUtil.getExpirationMs();
        return ResponseEntity.ok(new AuthResponse(token, Long.toString(expiresAt)));
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("Email already exists");
        }

        Set<Role> roles = (request.getRoles() == null || request.getRoles().isEmpty()) ?
                Set.of(Role.ROLE_USER, Role.ROLE_GUEST)
                : request.getRoles().stream().map(Role::valueOf).collect(Collectors.toSet());

        User user = new User(request.getEmail(), request.getName(), request.getSurname(), passwordEncoder.encode(request.getPassword()), roles );
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
