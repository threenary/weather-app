package com.threenary.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.threenary.errors.NotFoundException;
import com.threenary.weather.domain.Condition;
import com.threenary.weather.provider.YahooFetcher;

public class YahooRepositoryTest {

	@Mock
	YahooFetcher fetcher;

	YahooRepository testSubject;

	@Before
	public void setUp() {
		initMocks(this);
		testSubject = new YahooRepository(fetcher);
	}

	@Test
	public void isInformationTooOldTestHappyPath() throws ParseException {
		String timeStamp = "Sat, 16 Sep 2017 06:00 PM CEST";

		assertThat(testSubject.informationIsTooOld(timeStamp), is(true));
	}

	@Test
	public void isInformationTooOldTestUnhappyPath() throws ParseException {
		String timeStamp = "Sat, 16 Sep 2099 10:00 AM CEST";

		assertThat(testSubject.informationIsTooOld(timeStamp), is(false));
	}

	@Test
	public void getWeatherTestHappyPath() throws IOException {
		//Given
		JsonObject jsonObject = (new JsonParser()).parse(json).getAsJsonObject();

		when(fetcher.fetchWeatherForCity(anyString())).thenReturn(jsonObject);

		//When
		Condition weather = testSubject.getWeather("CityName");

		//Then
		assertThat(weather, is(notNullValue()));
		assertThat(weather.getTemp(), is(equalTo("50")));
		assertThat(weather.getText(), is(equalTo("Mostly Cloudy")));
		assertThat(weather.getCelsiusTemperature(), is(equalTo(0)));
	}
	
	
	@Test(expected=NotFoundException.class)
	public void getWeatherTestUnhappyPath() throws IOException {
		//Given
		when(fetcher.fetchWeatherForCity(anyString())).thenThrow(new IOException());

		//When
		Condition weather = testSubject.getWeather("CityName");

		//Then
		assertThat(weather, is(notNullValue()));
		assertThat(weather.getTemp(), is(equalTo("50")));
		assertThat(weather.getText(), is(equalTo("Mostly Cloudy")));
		assertThat(weather.getCelsiusTemperature(), is(equalTo(0)));
	}

	private static final String json = "{\"query\": {\r\n" + 
			"    \"created\": \"2017-09-16T09:37:19Z\",\r\n" + 
			"    \"count\": 1,\r\n" + 
			"    \"lang\": \"en-US\",\r\n" + 
			"    \"results\": {\"channel\": {\r\n" + 
			"        \"atmosphere\": {\r\n" + 
			"            \"rising\": \"0\",\r\n" + 
			"            \"visibility\": \"16.1\",\r\n" + 
			"            \"humidity\": \"87\",\r\n" + 
			"            \"pressure\": \"1004.0\"\r\n" + 
			"        },\r\n" + 
			"        \"image\": {\r\n" + 
			"            \"width\": \"142\",\r\n" + 
			"            \"link\": \"http://weather.yahoo.com\",\r\n" + 
			"            \"title\": \"Yahoo! Weather\",\r\n" + 
			"            \"url\": \"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif\",\r\n" + 
			"            \"height\": \"18\"\r\n" + 
			"        },\r\n" + 
			"        \"item\": {\r\n" + 
			"            \"condition\": {\r\n" + 
			"                \"date\": \"Sat, 16 Sep 2017 10:00 AM CEST\",\r\n" + 
			"                \"temp\": \"50\",\r\n" + 
			"                \"code\": \"28\",\r\n" + 
			"                \"text\": \"Mostly Cloudy\"\r\n" + 
			"            }\r\n" + 
			"          }\r\n" + 
			"        }\r\n" + 
			"    }\r\n" + 
			"  }\r\n" + 
			"}";

}
