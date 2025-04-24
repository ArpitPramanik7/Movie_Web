package com.arpit.SpringMovies.Controller;

//import com.arpit.SpringMovies.Model.User;
import com.arpit.SpringMovies.Service.AuthService;
import com.arpit.SpringMovies.Dto.AuthRequest;
import com.arpit.SpringMovies.Dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
//@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        return authService.registerUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return authService.authenticateUser(request);
    }
}
