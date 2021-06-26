package com.ksmart.authen.repository;

import com.ksmart.authen.model.ConfirmationToken;
import com.ksmart.authen.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
    ConfirmationToken findByUser(User user);
}