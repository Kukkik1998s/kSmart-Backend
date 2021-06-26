package com.ksmart.authen.repository;

import com.ksmart.authen.model.Patient;
import com.ksmart.authen.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PatientRepository extends MongoRepository<Patient, String> {
    Optional<Patient> findByNameAndLastname(String name, String lastname);
    Optional<Patient> findByUser(User user);
}
