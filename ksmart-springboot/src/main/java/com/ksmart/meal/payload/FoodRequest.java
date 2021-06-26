package com.ksmart.meal.payload;

import com.ksmart.meal.model.Image;
import com.ksmart.meal.model.Ingredient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class FoodRequest {
    //private String imgUrl;
    @NotBlank
    @Size(max = 20)
    private String name;
    @DBRef
    private List<Ingredient> ingredients;
    private String category;

    private MultipartFile image;

   /* @DBRef
    private Image image;*/

    private double phosphate;

    public double getPhosphate() {
        return phosphate;
    }

    public void setPhosphate(double phosphate) {
        this.phosphate = phosphate;
    }

   /* public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }*/

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
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
}
