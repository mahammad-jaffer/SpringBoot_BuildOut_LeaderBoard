package com.example.LeaderBoard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.LeaderBoard.dto.User;
import com.example.LeaderBoard.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/say")
	public String sayHello() {
        // List<User> usersList = new ArrayList<User>();
        userRepository.findAll();
		return "Hello Spring boot";
	}

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {

        System.out.println("=======================");
        System.out.println("In here");
        System.out.println("=======================");
        List<User> usersList = new ArrayList<User>();
        userRepository.findAll().forEach(usersList::add);

        if(usersList.isEmpty()) {
            System.out.println("=======================");
        System.out.println("In here");
        System.out.println("=======================");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(usersList, HttpStatus.OK);

    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        
        Optional<User> userData = userRepository.findById(userId);

        if(userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @SuppressWarnings("null")
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try{
            User newUser = userRepository.save(new User(user.getUserId(), user.getUserName()));
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateScore(@PathVariable("userId") String userId, @RequestBody User user) {
        Optional<User> userDate = userRepository.findById(userId);

        if(userDate.isPresent()) {
            User _user = userDate.get();
            _user.setScore(user.getScore());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{userId}") 
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") String userId) {
        try{
            userRepository.deleteById(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}