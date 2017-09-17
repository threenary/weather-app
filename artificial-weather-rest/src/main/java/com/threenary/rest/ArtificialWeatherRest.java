package com.threenary.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.threenary.repository.YahooRepository;
import com.threenary.weather.domain.CityList;
import com.threenary.weather.domain.Condition;

/**
 * Root resource (exposed at "artificial" path)
 */
@Path("artificial")
public class ArtificialWeatherRest {

	/**
	 * Health check
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getIt() {
		String response = new Gson().toJson("Hello Artificial World!");
		System.out.println("REQUEST /cities: " + response);
		return response;
	}

	/**
	 * Returns the weather for the given city
	 * 
	 * @param city
	 * 
	 * @throws IOException
	 */
	@GET
	@Path("{city}")
	@Produces(MediaType.APPLICATION_JSON)
	public Condition getWeather(@PathParam("city") String city) throws IOException {
		Condition response = YahooRepository.getInstance().getWeather(city);
		System.out.println(String.format("REQUEST /%s: %s", city, new Gson().toJson(response)));
		return response;
	}

	/**
	 * Returns the weather for the given city
	 * 
	 * @param city
	 */
	@GET
	@Path("cities")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCities() {
		String response = new Gson().toJson(CityList.getInstance());
		System.out.println("REQUEST /cities: " + response);
		return response;
	}

}
