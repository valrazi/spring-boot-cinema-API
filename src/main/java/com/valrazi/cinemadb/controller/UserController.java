package com.valrazi.cinemadb.controller;

import com.valrazi.cinemadb.model.User;
import com.valrazi.cinemadb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;
    @GetMapping
    public ResponseEntity<Object> getAllUser(){
        return service.getAllUser();
    }
    @PostMapping
    public ResponseEntity<Object> addNewUser(@RequestBody User newUser){
        return service.addNewUser(newUser);
    }
    @DeleteMapping("/{uname}")
    public ResponseEntity<Object> deleteUser(@PathVariable String uname){
        return service.deleteUser(uname);
    }

    @PatchMapping("/{uname}")
    public ResponseEntity<Object> updateUser(@PathVariable String uname, @RequestBody User user){
        return service.updateUser(uname, user);
    }
}
