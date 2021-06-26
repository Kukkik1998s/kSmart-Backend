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

@Document(collection = "ingredients")
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

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
	private Date latestUpdate;
	//private Double calciumValue;

	//private Binary image;


	public Date getLatestUpdate() {
		return latestUpdate;
	}

	public void setLatestUpdate(Date latestUpdate) {
		this.latestUpdate = latestUpdate;
	}

	public Ingredient() {
		
	}
	public Ingredient(String category, String name, Double quantity, String unit, Double phosphorusValue) {
		//this.calciumValue = calciumValue;
		this.category = category;
		this.name = name;
		this.phosphorusValue = phosphorusValue;
		this.quantity = quantity;
		this.unit = unit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	public Double getPhosphorusValue() {
		return phosphorusValue;
	}
	public void setPhosphorusValue(Double phosphorusValue) {
		this.phosphorusValue = phosphorusValue;
	}
	/*public Double getCalciumValue() {
		return calciumValue;
	}
	public void setCalciumValue(Double calciumValue) {
		this.calciumValue = calciumValue;
	}*/

	/*public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}*/

	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	/*public String displayIngredient() {
		return "name: "+this.name+" category: "+ this.category+" quantity: "+this.quantity.toString()+" "+this.unit+
				" phosphorus value: "+this.phosphorusValue.toString()+" url: "+this.imgUrl;
	}*/
	
	
	
}
