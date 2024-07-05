package com.example.LeaderBoard.service;

import java.util.List;
import java.util.Optional;

import com.example.LeaderBoard.dto.User;


public interface LeaderBoardService {

    public List<User> getUsers();

    public void delete(String userId);

    public Optional<User> getUserById(String userId);

    public User createUser(User user);

    public Optional<User> updateUserScore(String userId, User user);
    
} 
