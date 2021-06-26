package com.ksmart.meal.repository;

import com.ksmart.authen.model.User;
import com.ksmart.meal.model.Food;
import com.ksmart.meal.model.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MealRepository extends MongoRepository<Meal, String> {
    //Optional<Meal> findByDate(User user, Date date);
    List<Meal> findByUsernameAndDatetimeBetween(String username, Date start, Date end);
    List<Meal> findByUsername(String username);
}
