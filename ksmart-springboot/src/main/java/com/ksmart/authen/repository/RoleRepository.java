package com.ksmart.authen.repository;

import com.ksmart.authen.model.ERole;
import com.ksmart.authen.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
