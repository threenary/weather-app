package com.threenary.weather.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

public class WeatherProvider {
	private static final String yahooWeather = "https://query.yahooapis.com/v1/public/yql?q=";
	private static final String query = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")";
	private static final String format = "&format=json";	


	public static void main(String[] args) throws IOException {
		
		
		
		
		String city = "Paris";
		
		//select * from weather.forecast where woeid in (select woeid from geo.places(1) where text="paris")
		//String request = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22paris%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		
		String baseUrl = "https://query.yahooapis.com/v1/public/yql?q=";
		String query = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", city);
		String fullUrlStr = yahooWeather + URLEncoder.encode(query, "UTF-8") + format;

		URL fullUrl = new URL(fullUrlStr);
		InputStream is = fullUrl.openStream();

		StringBuilder textBuilder = new StringBuilder();
	    try (Reader reader = new BufferedReader(new InputStreamReader
	      (is, Charset.forName(StandardCharsets.UTF_8.name())))) {
	        int c = 0;
	        while ((c = reader.read()) != -1) {
	            textBuilder.append((char) c);
	        }
	    }
	    
	    JSONObject object = new JSONObject(textBuilder.toString());

		System.out.println(object.toString(4));

		is.close();
	}
}
