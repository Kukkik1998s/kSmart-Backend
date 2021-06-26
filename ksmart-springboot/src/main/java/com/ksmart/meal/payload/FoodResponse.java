package com.ksmart.meal.payload;

import com.ksmart.meal.model.Ingredient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FoodResponse {
    private String name;
    private List<Ingredient> ingredients;
    private String category;
    private double phosphate;

    public FoodResponse(String name, List<Ingredient> ingredients, String category, double phosphate) {
        this.name = name;
        this.ingredients = ingredients;
        this.category = category;
        this.phosphate = phosphate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPhosphate() {
        return phosphate;
    }

    public void setPhosphate(double phosphate) {
        this.phosphate = phosphate;
    }
}
