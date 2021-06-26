package com.ksmart.meal.service;

import com.ksmart.meal.model.Food;
import com.ksmart.meal.model.Ingredient;
import com.ksmart.meal.repository.FoodRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/*@Service
public class FoodService {
    @Autowired
    FoodRepository foodRepository;

    public String addFood(Food food, MultipartFile image) throws IOException {
        food.setImage(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        foodRepository.save(food);
        return "success";
    }

    public String updateImage(Food food, MultipartFile image) throws IOException{
        food.setImage(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        foodRepository.save(food);
        return "success";
    }

    public List<Food> getFoods() {
        return foodRepository.findAll();
    }

    public Optional<Food> findById(String id) {
        return foodRepository.findById(id);
    }
}*/
