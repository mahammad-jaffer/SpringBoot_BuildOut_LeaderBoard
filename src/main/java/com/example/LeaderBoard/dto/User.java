package com.example.LeaderBoard.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
// @Builder
public class User {

    @Id
    private String userId;

    private String userName;
    private String score;
    private String [] badges;

    public User(String userId2, String userName2) {
        this.userId   = userId2;
        this.userName = userName2;
    }
    
}
