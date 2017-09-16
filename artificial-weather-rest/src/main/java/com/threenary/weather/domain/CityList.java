package com.threenary.weather.domain;

import java.util.ArrayList;

public class CityList {

	private static final ArrayList<City> cityList = new ArrayList<>();

	static {
		cityList.add(new City("Paris"));
		cityList.add(new City("Barcelona"));
		cityList.add(new City("Buenos Aires"));
		cityList.add(new City("Madrid"));
		cityList.add(new City("Berlin"));
		cityList.add(new City("Sarajevo"));
		cityList.add(new City("Kiev"));
		cityList.add(new City("Stuttgart"));
	}

	public static ArrayList<City> getInstance() {
		return cityList;
	}
}