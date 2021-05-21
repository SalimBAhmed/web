package com.web.model;

import java.util.List;
import java.util.ArrayList;

public class City {

	
	private int city_id;
	private String city_name;
	private String img;
	private String city_desc;
	private List<String> places;
	private List<String> places_desc;
	private List<String> places_img;
	
	public City() {
		this.city_id = 0;
		this.city_name = "";
		this.img = "";
		this.city_desc = "";
		this.places = new ArrayList<String>();
		this.places_desc = new ArrayList<String>();
		this.places_img = new ArrayList<String>();
	}
	public City(int city_id, String city_name, String img, String city_desc) {
		this.city_id = city_id;
		this.city_name = city_name;
		this.img = img;
		this.city_desc = city_desc;
		this.places = new ArrayList<String>();
		this.places_desc = new ArrayList<String>();
		this.places_img = new ArrayList<String>();
	}
	public City(int city_id, String city_name, String img, String city_desc, List<String> places, List<String> places_desc, List<String> places_img) {
		this.city_id = city_id;
		this.city_name = city_name;
		this.img = img;
		this.city_desc = city_desc;
		this.places = places;
		this.places_desc = places_desc;
		this.places_img = places_img;
	}
	
	// Getters and Setters 
	public int getCityId() {
		return this.city_id;
	}
	public void setCityId(int city_id) {
		this.city_id = city_id;
	}

	public String getCityName() {
		return this.city_name;
	}
	public void setCityName(String city_name) {
		this.city_name = city_name;
	}
	
	public String getImg() {
		return this.img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getCityDesc() {
		return this.city_desc;
	}
	public void setCityDesc(String city_desc) {
		this.city_desc = city_desc;
	}
	
	public List<String> getPlaces() {
		return this.places;
	}
	public void getPlaces(List<String> places) {
		this.places = places;
	}
	
	public List<String> getPlacesDesc() {
		return this.places_desc;
	}
	public void getPlacesDesc(List<String> places_desc) {
		this.places_desc = places_desc;
	}
	
	public List<String> getPlacesImg() {
		return this.places_img;
	}
	public void getPlacesImg(List<String> places_img) {
		this.places_img = places_img;
	}
}
