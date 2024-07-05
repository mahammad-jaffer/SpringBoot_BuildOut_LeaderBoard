package com.example.LeaderBoard.controller;

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
import com.example.LeaderBoard.service.LeaderBoardService;

@RestController
public class UserController {

    @Autowired
    private LeaderBoardService leaderBoardService;


    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> usersList = leaderBoardService.getUsers();
        if(usersList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Users Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usersList);
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") String userId) {
        Optional<User> userData = leaderBoardService.getUserById(userId);
        if(userData.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userData.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with given Id is not found");
        }
    }


    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try{
            if(user.getUserId() == null || user.getUserName() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide both UserId and UserName");
            }
            User newUser = leaderBoardService.createUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(newUser);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateScore(@PathVariable("userId") String userId, @RequestBody User user) {
        try {
            Optional<User> updatedUser = leaderBoardService.updateUserScore(userId, user);
            if(updatedUser.isPresent())
                return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
            else   
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found/ Please provide valid score");
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @DeleteMapping("/users/{userId}") 
    public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId) {
        try{
            leaderBoardService.delete(userId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}