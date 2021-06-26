package com.ksmart.meal.payload;

import com.ksmart.authen.model.Patient;
import com.ksmart.authen.model.User;
import com.ksmart.meal.model.Food;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

public class MealRequest {

    @NotBlank
    String  datetime;
    private boolean savelist;
    @NotBlank
    private String mealType;
    private String note;
    @DBRef
    private List<Food> dishes;
    private String username;


    public MealRequest(String username, String datetime, boolean savelist, String mealType, List<Food> dishes) {
        this.username = username;
        this.datetime = datetime;
        this.savelist = savelist;
        this.mealType = mealType;
        this.dishes = dishes;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String username) {
        this.username = username;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public boolean isSavelist() {
        return savelist;
    }

    public void setSavelist(boolean savelist) {
        this.savelist = savelist;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Food> getDishes() {
        return dishes;
    }

    public void setDishes(List<Food> dishes) {
        this.dishes = dishes;
    }
}
