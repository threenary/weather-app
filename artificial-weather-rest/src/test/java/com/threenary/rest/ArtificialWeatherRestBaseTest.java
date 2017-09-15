package com.threenary.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.threenary.main.Main;

import static org.junit.Assert.assertEquals;

public class ArtificialWeatherRestBaseTest {
	
	private final String PATH = "PATH: ";
	private final String SEPARATOR = "\n==========================\n";

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

	protected void logging(String url, String log) {
		System.out.println(
				new StringBuffer().append(PATH).append(url).append(SEPARATOR).append(log).append(SEPARATOR).toString());
	}

//    @Before
//    public void setUp() throws Exception {
//        // start the server
//        server = Main.startServer();
//        // create the client
//        Client c = ClientBuilder.newClient();
//
//        // uncomment the following line if you want to enable
//        // support for JSON in the client (you also have to uncomment
//        // dependency on jersey-media-json module in pom.xml and Main.startServer())
//        // --
//        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());
//
//        target = c.target(Main.BASE_URI);
//    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("myresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }
}
