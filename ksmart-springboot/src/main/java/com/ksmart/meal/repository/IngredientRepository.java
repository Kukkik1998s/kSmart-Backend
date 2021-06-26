package com.ksmart.meal.repository;

import com.ksmart.meal.model.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IngredientRepository extends MongoRepository<Ingredient, String> {
    Optional<Ingredient> findById(String id);
    //String getId(Ingredient i);

}
