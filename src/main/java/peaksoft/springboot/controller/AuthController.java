package peaksoft.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.springboot.dto.AuthResponse;
import peaksoft.springboot.dto.RegisterRequest;
import peaksoft.springboot.entity.User;
import peaksoft.springboot.security.JwtProvider;
import peaksoft.springboot.service.UserService;

@RestController

public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider provider;
    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        userService.saveUser(user);
        return "OK";

    }
    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody RegisterRequest request){
        User user = userService.findByLoginAndPassword(request.getEmail(), request.getPassword());
        String token = provider.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
