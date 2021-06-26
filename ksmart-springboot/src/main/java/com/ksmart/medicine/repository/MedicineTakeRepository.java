package com.ksmart.medicine.repository;

import com.ksmart.medicine.model.Medicine;
import com.ksmart.medicine.model.MedicineTake;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MedicineTakeRepository extends MongoRepository<MedicineTake,String> {
    List<MedicineTake> findByUsername(String username);
    List<MedicineTake> findByUsernameAndDateBetween(String username, Date start, Date end);
}
