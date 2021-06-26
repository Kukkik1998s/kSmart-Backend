package com.ksmart.meal.model;

import com.ksmart.authen.model.Patient;
import com.ksmart.authen.model.User;
import com.ksmart.meal.model.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import java.util.*;

import java.text.*;

@Document(collection = "meals")
public class Meal {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@NotBlank
	private Date datetime;
	private boolean savelist;
	@NotBlank
	private String mealType;

	private String note;

	private Double totalPhosphate;


	private String username;

	@DBRef
	private List<Food> dishes;

	private Date latestUpdate;


	public Meal(String username, Date datetime, boolean savelist, String mealType , List<Food> dishes) {
		this.username = username;
		this.datetime = datetime;
		this.savelist = savelist;
		this.mealType = mealType;
		this.dishes = dishes;
		setTotalPhosphate();
	}

	public Date getLatestUpdate() {
		return latestUpdate;
	}

	public void setLatestUpdate(Date latestUpdate) {
		this.latestUpdate = latestUpdate;
	}

	public void setTotalPhosphate() {
		double sum = 0;
		System.out.println(this.dishes);
		for(Food f: this.dishes){
			System.out.println(f);
			System.out.println("food P: "+f.getPhosphate());
			sum+=f.getPhosphate();
			System.out.println(sum);
		}
		this.totalPhosphate = sum;
	}

	public Double getTotalPhosphate() {
		return totalPhosphate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return username;
	}

	public void setUser(String user) {
		this.username = user;
	}

	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
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
	
	/*public String displayMeal() {
		SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		String save = "not save";
		if(this.savelist) save = "save";
		String selectedDishes ="";
		for(Food f: this.dishes) {
			selectedDishes = selectedDishes+"\n"+f.getName();
		}
		return "Date and time: "+ft.format(this.datetime)+"\nMeal Type: "+this.mealType+
				"\nSave? "+save+"\nNote: "+this.note+"\nSelected Dishes: "+selectedDishes;
	}*/

}
