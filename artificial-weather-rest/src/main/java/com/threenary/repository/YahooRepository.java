package com.threenary.repository;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.threenary.errors.JsonError;
import com.threenary.errors.NotFoundException;
import com.threenary.weather.domain.Condition;
import com.threenary.weather.domain.YahooDataModel;
import com.threenary.weather.provider.YahooFetcher;

public class YahooRepository {

	private YahooFetcher fetcher;
	
	/**
	 * Constructor
	 * 
	 * @param fetcher
	 */
	public YahooRepository(YahooFetcher fetcher) {
		super();
		this.fetcher = fetcher;
	}

	/**
	 * Calls the weather service to retrieve actual data
	 * 
	 * @param city
	 */
	public YahooDataModel fetchWeather(String city) {
		JsonObject json;
		try {
			System.out.println(String.format("FETCHING DATA /%s", city));
			json = fetcher.fetchWeatherForCity(city);
		} catch (IOException e) {
			throw new NotFoundException(new JsonError("Error", "Weather for " + city + "could not be found"));
		}
		updateCache(city, json);
		return cache().get(city);
	}

	/**
	 * Updates the cached data for the given city
	 * 
	 * @param city
	 * @param json
	 */
	public void updateCache(String city, JsonObject json) {
		cache().put(city, parseToModel(json));
	}

	/**
	 * Retrieves the weather stored in cache
	 * 
	 * @param city
	 */
	public YahooDataModel retrieveFromCache(String city) {
		YahooDataModel data = cache().get(city);
		if (fetchNeeded(data)) {
			data = fetchWeather(city);
		}else {
			System.out.println(String.format("CATCHED DATA /%s", city));
		}
		return data;
	}

	public Condition getWeather(String city) {
		YahooDataModel data = this.retrieveFromCache(city);
		return data.getCondition();
	}

	/**
	 * Parse a JsonObject to the real YahooDataModel
	 * 
	 * @param json
	 */
	private YahooDataModel parseToModel(JsonObject json) {
		return new Gson().fromJson(json, YahooDataModel.class);
	}

	/**
	 * Determines the cache refresh need conditions
	 * 
	 * @param data
	 * @throws ParseException
	 */
	private boolean fetchNeeded(YahooDataModel data) {
		return (data == null) || informationIsTooOld(data.getCondition().getDate());
	}

	/**
	 * Determines the time stamp received is older than 60 minutes
	 * 
	 * @param timeStamp
	 * @throws ParseException
	 */
	public boolean informationIsTooOld(String timeStamp) {
		SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm a z", Locale.US);
		Date parseDate;
		try {
			parseDate = format.parse(timeStamp);
		} catch (ParseException e) {
			return false;
		}
		long diffInMillies = new Date().getTime() - parseDate.getTime();
		return diffInMillies > 3600*60*60;
	}
	
	private WeatherCache cache() {
		return WeatherCache.getInstance();
	}
}
