package com.valrazi.cinemadb.controller.user;

import com.valrazi.cinemadb.model.User;
import com.valrazi.cinemadb.repository.UserRepository;
import com.valrazi.cinemadb.security.jwt.JwtUtils;
import com.valrazi.cinemadb.security.services.UserDetailsServiceImpl;
import com.valrazi.cinemadb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.valrazi.cinemadb.response.ResponseHandler.generateResponse;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;



    @GetMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> getUserDetail(@RequestHeader(value = "Authorization")String token) {
        String jwtToken = token.substring(7, token.length());
        User userFound = null;
        if(jwtToken != null && jwtUtils.validateTokenJwt(jwtToken)){
            String username = jwtUtils.getUserNameFromToken(jwtToken);
            userFound = userRepository.findByUname(username).orElseThrow( () -> new UsernameNotFoundException("Uname not found"));

        }
        return generateResponse("OK", HttpStatus.OK, userFound.getUname());
    }

    @PatchMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> updateUser(@RequestHeader(value = "Authorization")String token, @RequestBody User user) {
        String jwtToken = token.substring(7, token.length());
        User userFound = null;
        if(jwtToken != null && jwtUtils.validateTokenJwt(jwtToken)){
            String username = jwtUtils.getUserNameFromToken(jwtToken);
            userFound = userRepository.findByUname(username).orElseThrow( () -> new UsernameNotFoundException("Uname not found"));
        }

        return generateResponse("OK", HttpStatus.OK, userService.updateUser(userFound.getUserId(), user));
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> deleteUser(@RequestHeader(value = "Authorization") String token){
        String jwtToken = token.substring(7, token.length());
        User userFound = null;
        if(jwtToken != null && jwtUtils.validateTokenJwt(jwtToken)){
            String username = jwtUtils.getUserNameFromToken(jwtToken);
            userFound = userRepository.findByUname(username).orElseThrow( () -> new UsernameNotFoundException("Uname not found"));

        }
        return generateResponse("OK", HttpStatus.OK, userService.deleteUser(userFound.getUserId()));
    }

}
