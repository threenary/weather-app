package com.threenary.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.threenary.weather.domain.DataModel;
import com.threenary.weather.provider.YahooFetcher;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/artificial")
public class ArtificialWeatherRest {
	
	YahooFetcher fetcher = new YahooFetcher();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {   	
        return "Got it!";
    }

    @GET
	@Path("{city}")
	@Produces(MediaType.APPLICATION_JSON)
	public DataModel getSurveyResults(@PathParam("city") String city) throws IOException {
		JsonObject json = fetcher.fetchWeatherForCity(city);
				
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput = gson.toJson(json);
    	System.out.println(jsonOutput);   	
    			
		return fetcher.getObjectModel(json);
	}
}
