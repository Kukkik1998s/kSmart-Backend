package com.ksmart.meal.payload;

import com.ksmart.meal.model.Image;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class IngredientRequest {

    private String category;
    @NotBlank
    @Size(max = 20)
    private String name;
    @NotBlank
    private Double quantity;
    @NotBlank
    @Size(max = 10)
    private String unit;
    @NotBlank
    private Double phosphorusValue;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPhosphorusValue() {
        return phosphorusValue;
    }

    public void setPhosphorusValue(Double phosphorusValue) {
        this.phosphorusValue = phosphorusValue;
    }

   /* public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }*/
}
