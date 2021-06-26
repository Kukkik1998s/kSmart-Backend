package com.ksmart.meal.service;


import com.ksmart.meal.model.Ingredient;
import com.ksmart.meal.repository.IngredientRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/*@Service
public class IngredientServiceImpl implements IngredientService{
    @Autowired
    IngredientRepository ingredientRepository;

    public String addIngredient(Ingredient ingredient, MultipartFile image) throws IOException {
        ingredient.setImage(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        ingredientRepository.save(ingredient);
        return "success";
    }

    public String updateImage(Ingredient ingredient, MultipartFile image) throws IOException{
        ingredient.setImage(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        ingredientRepository.save(ingredient);
        return "success";
    }


    public List<Ingredient> getIngredients(){
        return ingredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        return ingredientRepository.findById(id);
    }
}*/
