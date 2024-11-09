package com.seg7.backendproject;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;

public interface AccountRepository extends MongoRepository<Account, String> {
    @Query("{ 'category': ?0, 'time': { $gt: ?1, $lt: ?2 } }")
    ArrayList<Account> getAccountsByCategoryAndTimeBetween(String category, String startTime, String endTime);

    @Query("{ 'time': { $gt: ?1, $lt: ?2 } }")
    ArrayList<Account> getAccountsByTimeAfterAndTimeBetween(String startTime, String endTime);
}
