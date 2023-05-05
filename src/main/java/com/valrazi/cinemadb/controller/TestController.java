package com.valrazi.cinemadb.controller;

import com.valrazi.cinemadb.model.User;
import com.valrazi.cinemadb.repository.UserRepository;
import com.valrazi.cinemadb.security.jwt.JwtUtils;
import com.valrazi.cinemadb.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User userAccess(@RequestHeader(value = "Authorization")String token) {
        String jwtToken = token.substring(7, token.length());
        User userFound = null;
        if(jwtToken != null && jwtUtils.validateTokenJwt(jwtToken)){
            String username = jwtUtils.getUserNameFromToken(jwtToken);
            userFound = userRepository.findByUname(username).orElseThrow( () -> new UsernameNotFoundException("Uname not found"));
        }
        return userFound;
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
