package com.threenary.repository;

import java.util.HashMap;

import com.threenary.weather.domain.YahooDataModel;

public class WeatherCache {
	private static WeatherCache instance = null;
	
	private HashMap<String, YahooDataModel> cache;

	protected WeatherCache() {
		this.cache = new HashMap<>();
	}

	public static WeatherCache getInstance() {
		if (instance == null) {
			instance = new WeatherCache();
		}
		return instance;
	}
	
	public YahooDataModel get(String city) {
		return cache.get(city);
	}
	
	public void put(String city, YahooDataModel data) {
		cache.put(city, data);
	}
}