package com.ksmart.authen.repository;

import com.ksmart.authen.model.Role;
import com.ksmart.authen.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername (String username);
    Optional<User> findByEmail (String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
   // List<User> getAllUsers();
}
