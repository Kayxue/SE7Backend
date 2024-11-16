package com.seg7.backendproject;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface UserRepository extends MongoRepository<User,String> {
    @Query("{ 'email' : ?0 }")
    public User findByEmail(String email);
}
