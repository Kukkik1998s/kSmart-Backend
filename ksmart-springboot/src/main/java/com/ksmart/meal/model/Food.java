package com.ksmart.meal.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Document(collection = "foods")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@NotBlank
	@Size(max = 20)
	private String name;
	@DBRef
	private List<Ingredient> ingredients;
	private String category;
	private double phosphate;
	private Date latestUpdate;
	//private  double calcium;

	//Binary image;

	/*@DBRef
	private Image image;*/
	
	public Food() {
		
	}
	
	public Food(String name, List<Ingredient> ingredients, String category,double phosphate) {
		this.ingredients = ingredients;
		this.name = name;
		this.category =category;
		this.phosphate = phosphate;
	}


	public Date getLatestUpdate() {
		return latestUpdate;
	}

	public void setLatestUpdate(Date latestUpdate) {
		this.latestUpdate = latestUpdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getPhosphate() {
		return phosphate;
	}

	public void setPhosphate(double phosphate) {
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
	public String getCategory(){ return category;}
	public void setCategory(String category){this.category=category;}
	/*public String displayFood() {
		String first = "Food name: "+this.name+"Image location: "+this.imgUrl+"List of ingredients:";
		String second ="";
		for(Ingredient i: this.ingredients) {
			second = second +"\n"+ i.getName() .toString();
		}
		return first+second;	
		
	}*/
	
	
}
