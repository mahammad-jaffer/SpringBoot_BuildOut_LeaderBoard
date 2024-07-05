package com.example.LeaderBoard.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LeaderBoard.dto.User;
import com.example.LeaderBoard.repository.UserRepository;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        List<User> usersList = new ArrayList<User>();
        userRepository.findAll().forEach(usersList::add);
        Collections.sort(usersList, Comparator.comparing(User::getScore).reversed());
        return usersList;
    }    

    @Override
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(new User(user.getUserId(), user.getUserName(), 0));
    }

    @Override
    public Optional<User> updateUserScore(String userId, User user) {
        Optional<User> userDate = userRepository.findById(userId);
        if(userDate.isPresent()) {
            User _user              = userDate.get();
            Integer score           = user.getScore();
            if(!(score > 0 && score <= 100)) {
                return null;
            }
            List<String> userBadges = _user.getBadges();
            userBadges              = badgesUpdate(userBadges, score);
 
            _user.setBadges(userBadges);
            _user.setScore(score);
            
            userRepository.save(_user);            
            return Optional.ofNullable(_user);
        }
        return null;
    }

    public List<String> badgesUpdate(List<String> userBadges, Integer score) {
        String badge           = "";
        List<String> newBadges = new ArrayList<>();

        if(userBadges != null)              newBadges.addAll(userBadges);
        
        if(1 <= score && score < 30)        badge = "Code Ninja";
        else if(30 <= score && score < 60)  badge = "Code Champ";
        else                                badge = "Code Master";

        if(!newBadges.contains(badge))      newBadges.add(badge);

        return newBadges;
    }

    @Override
    public void delete(String userId) {
        userRepository.deleteById(userId);
    }
}
