package com.threenary.weather.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.threenary.weather.domain.DataModel;

public class YahooFetcher {
	
	private static final String yahooWeather = "https://query.yahooapis.com/v1/public/yql?q=";
	private static final String query = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")";
	private static final String format = "&format=json";	

	public String getUrlString(String city)
			throws UnsupportedEncodingException {

		StringBuilder sb = new StringBuilder();
		String parametrizedQuery = String.format(query, city);

		sb.append(yahooWeather);
		sb.append(URLEncoder.encode(parametrizedQuery, "UTF-8"));
		sb.append(format);
		return sb.toString();
	}

	public JsonObject fetchWeatherForCity(String city) throws IOException {
		String url = this.getUrlString(city);
		InputStream inputStream = (new URL(url)).openStream();
		String response = responseParser(inputStream);
		return (new JsonParser()).parse(response).getAsJsonObject();
	}
	
	public DataModel getObjectModel(JsonObject json) {
		return new Gson().fromJson(json, DataModel.class);
	}

	private String responseParser(InputStream inputStream) throws IOException {
		StringBuilder textBuilder = new StringBuilder();
		try (Reader reader = new BufferedReader(
				new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
			int c = 0;
			while ((c = reader.read()) != -1) {
				textBuilder.append((char) c);
			}
		}
		return textBuilder.toString();
	}
}
