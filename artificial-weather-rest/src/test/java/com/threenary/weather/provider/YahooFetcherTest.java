package com.threenary.weather.provider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.threenary.weather.domain.DataModel;

public class YahooFetcherTest {

	private YahooFetcher testSubject = new YahooFetcher();

	@Test
	public void getUrlString() throws UnsupportedEncodingException {
		String city = "Buenos Aires";

		String result = testSubject.getUrlString(city);

		assertThat(result, is(equalTo(
				"https://query.yahooapis.com/v1/public/yql?q=select+*+from+weather.forecast+where+woeid+in+%28select+woeid+from+geo.places%281%29+where+text%3D%22Buenos+Aires%22%29&format=json")));
	}

	@Test
	public void getYahooModelTest() {
		String json = "{\r\n" + 
				"  \"query\": {\r\n" + 
				"    \"count\": 1,\r\n" + 
				"    \"created\": \"2017-09-15T23:04:20Z\",\r\n" + 
				"    \"lang\": \"en-US\",\r\n" + 
				"    \"results\": {\r\n" + 
				"      \"channel\": {\r\n" + 
				"        \"units\": {\r\n" + 
				"          \"distance\": \"mi\",\r\n" + 
				"          \"pressure\": \"in\",\r\n" + 
				"          \"speed\": \"mph\",\r\n" + 
				"          \"temperature\": \"F\"\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";

		JsonObject jsonObject = (new JsonParser()).parse(json).getAsJsonObject();

		DataModel dm = testSubject.getObjectModel(jsonObject);

		assertThat(dm, is(notNullValue()));
		assertThat(dm.getQuery().getCount(), is(equalTo(1)));
		assertThat(dm.getQuery().getResults().getChannel().getUnits().getDistance(), is(equalTo("mi")));

	}

}
