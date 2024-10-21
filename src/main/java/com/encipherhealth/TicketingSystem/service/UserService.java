package com.encipherhealth.TicketingSystem.service;

import com.encipherhealth.TicketingSystem.model.User;
import com.encipherhealth.TicketingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    BCryptPasswordEncoder Encoder = new BCryptPasswordEncoder();

    public User createUser(User user){
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(Encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public List<User> getAllUser(){
        return repository.findAll();
    }

    public User getUserById(String userId){
        return repository.findById(userId).get();
    }

    public User modifyUser(User user){
        User existingUser = repository.findById(user.getUserId()).get();
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());
        existingUser.setLevel(user.getLevel());
        return repository.save(existingUser);
    }

    public String deleteUser(String userId){
        repository.deleteById(userId);
        return "User has been Deleted successfully";
    }
}
