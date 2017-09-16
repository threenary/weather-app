package com.threenary.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.threenary.main.Main;
import com.threenary.repository.YahooRepository;
import com.threenary.weather.domain.City;

public class ArtificialWeatherRestBaseTest {

	private HttpServer server;

	private WebTarget target;

	@Mock
	private YahooRepository repository;

	@Before
	public void setUp() throws Exception {
		server = Main.startServer();
		Client c = ClientBuilder.newClient();
		target = c.target(Main.BASE_URI);

		initMocks(this);
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
		assertThat("Hello World!", is(equalTo(responseMsg)));
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
