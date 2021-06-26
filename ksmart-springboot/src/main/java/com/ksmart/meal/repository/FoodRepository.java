package com.ksmart.meal.repository;

import com.ksmart.meal.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FoodRepository extends MongoRepository<Food, String> {
    //Optional<Food> findByName(String name);

    Optional<Food> findById(String s);
}
