package com.ksmart.medicine.repository;

import com.ksmart.medicine.model.Medicine;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MedicineRepository extends MongoRepository<Medicine,String> {
    @Override
    Optional<Medicine> findById(String s);
}
