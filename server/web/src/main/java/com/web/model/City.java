package com.web.model;

import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;

@XmlRootElement
public class City {

	
	private int cityId;
	private String cityName;
	private String img;
	private String cityDesc;
	@XmlElementWrapper
	@XmlElements(@XmlElement(name="place", type=String.class))
	private List<String> places;
	@XmlElementWrapper
	@XmlElements(@XmlElement(name="decription", type=String.class))
	private List<String> placesDesc;
	@XmlElementWrapper
	@XmlElements(@XmlElement(name="link", type=String.class))
	private List<String> placesImg;
	
	public City() {
		this.cityId = 0;
		this.cityName = "";
		this.img = "";
		this.cityDesc = "";
		this.places = new ArrayList<String>();
		this.placesDesc = new ArrayList<String>();
		this.placesImg = new ArrayList<String>();
	}
	public City(int cityId, String cityName, String img, String cityDesc) {
		this.cityId = cityId;
		this.cityName = cityName;
		this.img = img;
		this.cityDesc = cityDesc;
		this.places = new ArrayList<String>();
		this.placesDesc = new ArrayList<String>();
		this.placesImg = new ArrayList<String>();
	}
	public City(int cityId, String cityName, String img, String cityDesc, List<String> places, List<String> placesDesc, List<String> placesImg) {
		this.cityId = cityId;
		this.cityName = cityName;
		this.img = img;
		this.cityDesc = cityDesc;
		this.places = places;
		this.placesDesc = placesDesc;
		this.placesImg = placesImg;
	}
	
	// Getters and Setters 
	public int getCityId() {
		return this.cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return this.cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getImg() {
		return this.img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getCityDesc() {
		return this.cityDesc;
	}
	public void setCityDesc(String cityDesc) {
		this.cityDesc = cityDesc;
	}
	
	public List<String> getPlaces() {
		return this.places;
	}
	public void getPlaces(List<String> places) {
		this.places = places;
	}
	
	public List<String> getPlacesDesc() {
		return this.placesDesc;
	}
	public void getPlacesDesc(List<String> placesDesc) {
		this.placesDesc = placesDesc;
	}
	
	public List<String> getPlacesImg() {
		return this.placesImg;
	}
	public void getPlacesImg(List<String> placesImg) {
		this.placesImg = placesImg;
	}
}
