package com.ksmart.meal.controller;

import com.ksmart.authen.payload.response.MessageResponse;
import com.ksmart.meal.model.Food;
import com.ksmart.meal.model.Image;
import com.ksmart.meal.model.Ingredient;
import com.ksmart.meal.model.Meal;
import com.ksmart.meal.payload.*;
import com.ksmart.meal.repository.FoodRepository;
import com.ksmart.meal.repository.IngredientRepository;
import com.ksmart.meal.repository.MealRepository;
import com.ksmart.meal.service.ImageService;
import com.ksmart.medicine.model.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/meal")
public class MealController {

    @Autowired
    FoodRepository foodRepo;
    //FoodService foodService;

    @Autowired
    IngredientRepository ingredientRepo;
    //IngredientServiceImpl ingredientServiceImpl;

    @Autowired
    MealRepository mealRepo;

    @Autowired
    ImageService service;


    @PostMapping("/addfood")
    public ResponseEntity<?> addFood(@Valid @RequestBody FoodRequest foodRequest){
        Food food = new Food(foodRequest.getName(),foodRequest.getIngredients(),
                foodRequest.getCategory(), foodRequest.getPhosphate());
        food.setLatestUpdate(new Date());
        foodRepo.save(food);

        String id = food.getId();
        System.out.println(id);
        service.getRef(id);

        return ResponseEntity.ok(new MessageResponse(id));
    }

    @PostMapping("/addingredient")
    public ResponseEntity<?> addIngredient(@Valid @RequestBody IngredientRequest ingredientRequest){

        Ingredient ingredient = new Ingredient(ingredientRequest.getCategory(),ingredientRequest.getName(),ingredientRequest.getQuantity(),
                ingredientRequest.getUnit(),ingredientRequest.getPhosphorusValue());
        ingredient.setLatestUpdate(new Date());
        ingredientRepo.save(ingredient);

        String id = ingredient.getId();
        service.getRef(id);

        return ResponseEntity.ok(new MessageResponse(id));
    }

    @GetMapping("/ingredients")
    public List<Ingredient> ingredientList(){
        return ingredientRepo.findAll();
    }

    @GetMapping("/foods")
    public List<Food> foodList(){
        return  foodRepo.findAll();
    }

    @GetMapping("/foods/{id}")
    public Food getFood(@PathVariable("id") String id){
        Food food = foodRepo.findById(id).get();
        return  food;
    }

    @GetMapping("/ingredients/{id}")
    public Ingredient getIngredient(@PathVariable("id") String id){
        return  ingredientRepo.findById(id).get();
    }

    @PostMapping("/addmeal")
    public ResponseEntity<?> addMeal(@Valid @RequestBody MealRequest mealRequest) throws ParseException {


        //Date datetime, boolean savelist, String mealType, String note, List<Food> dishes
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String str = mealRequest.getDatetime();
        String[] dateStr = str.split("T");
        Date date = format.parse(dateStr[0]+" "+dateStr[1]);
        Meal meal = new Meal(mealRequest.getUser(),date,mealRequest.isSavelist(),mealRequest.getMealType(),
                mealRequest.getDishes());
        mealRepo.save(meal);

        return ResponseEntity.ok(new MessageResponse(meal.getId()));

    }

    @GetMapping("/getmeal/range")
    public List<Meal> mealInRange(@RequestParam String start,@RequestParam String end,@RequestParam String username) throws ParseException {
        //List<Meal> all = mealRepo.findAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(start);
        Date _date = sdf.parse(end);
        List<Meal> meals = mealRepo.findByUsernameAndDatetimeBetween(username,date,_date);
        return  meals;
    }

    @GetMapping("/getmeal")
    public List<Meal> mealList(@RequestParam String username){
        List<Meal> meals = mealRepo.findByUsername(username);
        return meals;
    }

    @PutMapping("/foods/{id}")
    public ResponseEntity<?> updateFood(@PathVariable("id") String id, @RequestBody FoodRequest request){
        Food food = foodRepo.findById(id).get();
        if(food.getName()!=request.getName()) food.setName(request.getName());
        if(food.getCategory()!=request.getCategory()) food.setCategory(request.getCategory());
        if(request.getIngredients()!=null) food.setIngredients(request.getIngredients());
        food.setPhosphate(request.getPhosphate());
        System.out.println(request.getPhosphate());
        food.setLatestUpdate(new Date());
        foodRepo.save(food);
        return ResponseEntity.ok(new FoodResponse(food.getName(),food.getIngredients(),food.getCategory(),
                food.getPhosphate()));
    }

    @PutMapping("/ingredients/{id}")
    public ResponseEntity<?> updateIngredient(@PathVariable("id") String id, @RequestBody IngredientRequest request){
        Ingredient ingredient = ingredientRepo.findById(id).get();
        if(request.getName()!=ingredient.getName()) ingredient.setName(request.getName());
        if(request.getCategory()!=ingredient.getCategory()) ingredient.setCategory(request.getCategory());
        if(request.getPhosphorusValue()!=ingredient.getPhosphorusValue()) ingredient.setPhosphorusValue(request.getPhosphorusValue());
        if(request.getQuantity()!=ingredient.getQuantity()) ingredient.setQuantity(request.getQuantity());
        if(request.getUnit()!=ingredient.getUnit()) ingredient.setUnit(request.getUnit());
        ingredient.setLatestUpdate(new Date());
        ingredientRepo.save(ingredient);
        return ResponseEntity.ok(new IngredientResponse(ingredient.getCategory(),ingredient.getName(),
                ingredient.getQuantity(),ingredient.getUnit(),ingredient.getPhosphorusValue()));
    }





}
