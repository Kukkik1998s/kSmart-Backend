package com.ksmart.meal.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class IngredientResponse {
    private String category;
    private String name;
    private Double quantity;
    private String unit;
    private Double phosphorusValue;

    public IngredientResponse(String category, String name, Double quantity, String unit, Double phosphorusValue) {
        this.category = category;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.phosphorusValue = phosphorusValue;
    }

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
}
