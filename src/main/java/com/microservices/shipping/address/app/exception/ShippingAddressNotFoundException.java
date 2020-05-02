package com.microservices.shipping.address.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShippingAddressNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3204705332368137965L;

	public ShippingAddressNotFoundException(String message) {
		super(message);
	}
}
