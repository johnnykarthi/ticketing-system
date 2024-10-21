package com.encipherhealth.TicketingSystem.controller;

import com.encipherhealth.TicketingSystem.model.User;
import com.encipherhealth.TicketingSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService service;

    // To create a new user

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        return service.createUser(user);
    }

    // To get all Users

    @GetMapping
    public List<User> getAllUser(){
        return service.getAllUser();
    }

    // To get specific User

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId){
        return service.getUserById(userId);
    }

    // To update User

    @PutMapping
    public User modifyUser(@RequestBody User user){
        return service.modifyUser(user);
    }

    // To Delete user

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return service.deleteUser(userId);
    }
}
