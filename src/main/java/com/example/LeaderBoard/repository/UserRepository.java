package com.example.LeaderBoard.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.LeaderBoard.dto.User;

public interface UserRepository extends MongoRepository<User, String> {
     
}