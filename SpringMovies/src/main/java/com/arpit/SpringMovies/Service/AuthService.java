package com.arpit.SpringMovies.Service;

import com.arpit.SpringMovies.Model.User;
import com.arpit.SpringMovies.Repo.UserRepository;
import com.arpit.SpringMovies.Dto.AuthRequest;
import com.arpit.SpringMovies.Dto.AuthResponse;
import com.arpit.SpringMovies.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<String> registerUser(AuthRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully");
    }

    public ResponseEntity<AuthResponse> authenticateUser(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}

