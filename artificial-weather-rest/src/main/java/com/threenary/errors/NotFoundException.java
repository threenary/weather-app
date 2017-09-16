package com.threenary.errors;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * NotFoundException
 */
public class NotFoundException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	/**
	 * Create a HTTP 404 (Not Found) exception.
	 */
	public NotFoundException() {
		super(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).build());
	}

	/**
	 * Create a HTTP 404 (Not Found) exception.
	 * 
	 * @param message
	 *            the String that is the entity of the 404 response.
	 */
	public NotFoundException(String message) {
		super(Response.status(Response.Status.NOT_FOUND).entity(message).type(MediaType.TEXT_PLAIN).build());
	}

	/**
	 * Create a HTTP 404 (Not Found) exception.
	 * 
	 * @param error
	 *            the JsonError that is the entity of the 404 response.
	 */
	public NotFoundException(JsonError error) {
		super(Response.status(Response.Status.NOT_FOUND).entity(error).type(MediaType.APPLICATION_JSON).build());
	}

}