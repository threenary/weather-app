package com.threenary.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.threenary.main.Main;
import com.threenary.weather.domain.City;

public class ArtificialWeatherRestBaseTest {

	private HttpServer server;

	private WebTarget target;

	@Before
	public void setUp() throws Exception {
		server = Main.startServer();
		Client c = ClientBuilder.newClient();
		target = c.target(Main.BASE_URI);
	}

	@After
	public void tearDown() throws Exception {
		server.shutdownNow();
	}

	/**
	 * Test health check
	 */
	@Test
	public void testHealthcare() {
		String responseMsg = target.path("artificial").request().get(String.class);
		assertThat(responseMsg, containsString("Hello Artificial World!"));
	}

	/**
	 * Test cities
	 */
	@Test
	public void testGetCities() {
		String responseMsg = target.path("artificial/cities").request().get(String.class);

		List<City> cities = new Gson().fromJson(responseMsg, new TypeToken<List<City>>() {
		}.getType());
		assertThat(cities.size(), is(equalTo(8)));
	}
}
