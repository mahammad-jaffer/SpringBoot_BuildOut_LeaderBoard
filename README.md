# SpringBoot Application for LeaderBoard


## Problem Statement
  Develop a RESTful API service using Spring Boot to manage the Leaderboard for a Coding Platform while using MongoDB to persist the data.


## Problem Description
  While coding platforms usually host multiple contests while maintaining numerous leaderboards, this assignment requires you to design a service for managing the leaderboard of a specific contest. Assume the platform has only one contest with a single leaderboard. The platform also gives virtual awards to the users called Badges based on their score.


## Endpoints
  + GET /users - Retrieve a list of all registered users
  + GET /users/{userId} - Retrieve the details of a specific user
  + POST /users - Register a new user to the contest
  + PUT /users/{userId} - Update the score of a specific user
  + DELETE /users/{userId} - Deregister a specific user from the contest


## PostMan Collection
  [https://elements.getpostman.com/redirect?entityId=37036953-d377ff43-3f9d-4c13-b24a-bea8208effe5&entityType=collection]
