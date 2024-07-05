package com.example.LeaderBoard.dto;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String userId;

    private String userName;
    private Integer score;
    private List<String> badges;

    public User(String userId2, String userName2, int score) {
        this.userId   = userId2;
        this.userName = userName2;
        this.score    = score;
    }
    
}
