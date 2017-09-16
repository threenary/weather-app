package com.threenary.weather.provider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class YahooFetcherTest {

	private YahooFetcher testSubject = new YahooFetcher();

	@Test
	public void getUrlString() throws UnsupportedEncodingException {
		String city = "Buenos Aires";

		String result = testSubject.getUrlString(city);

		assertThat(result, is(equalTo(
				"https://query.yahooapis.com/v1/public/yql?q=select+*+from+weather.forecast+where+woeid+in+%28select+woeid+from+geo.places%281%29+where+text%3D%22Buenos+Aires%22%29&format=json")));
	}
}
