package com.microservices.shipping.address.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.shipping.address.app.entity.ShippingAddress;
import com.microservices.shipping.address.app.exception.ShippingAddressNotFoundException;
import com.microservices.shipping.address.app.exception.UserNotFoundException;
import com.microservices.shipping.address.app.service.ShippingAddressService;

@RestController
public class ShippingAddressController {

	private static Logger LOGGER = LoggerFactory.getLogger(ShippingAddressController.class);
	
	@Autowired
	private ShippingAddressService shippingService;

	@GetMapping("/user/{userId}/shippingAddressList")
	public List<ShippingAddress> getShippingAddressList(@PathVariable String userId) throws UserNotFoundException {
		LOGGER.debug("fetching shipping address list for user");
		return shippingService.getShippingAddressListByUserId(userId);
	}

	@GetMapping("/user/{userId}/shippingAddress/{addressId}")
	public ShippingAddress getShippingAddressById(@PathVariable String userId, @PathVariable String addressId)
			throws ShippingAddressNotFoundException, UserNotFoundException {
		LOGGER.debug("fetching shipping address details with addressId :{},for userId: {}", addressId, userId);
		return shippingService.getShippingAddressById(userId, addressId);
	}

	@PostMapping("/user/{userId}/shippingAddress")
	@ResponseStatus
	public ResponseEntity<ShippingAddress> createShippingAddress(@PathVariable String userId,
			@RequestBody ShippingAddress shippingAddress) throws UserNotFoundException {
		LOGGER.debug("request for creating new shipping address for userId: {}", userId);
		return shippingService.createShippingAddress(userId, shippingAddress);
	}

	@DeleteMapping("/user/{userId}/shippingAddress/{addressId}")
	public void deleteShippingAddress(@PathVariable String userId, @PathVariable String addressId)
			throws UserNotFoundException {
		LOGGER.debug("request for deleting shipping address for userId: {}", userId);
		shippingService.deleteShippingAddress(userId, addressId);
	}

	@PutMapping("/user/{userId}/updateShippingAddress")
	@ResponseStatus
	public ResponseEntity<ShippingAddress> updateShippingAddress(@PathVariable String userId,
			@RequestBody ShippingAddress shippingAddress) throws UserNotFoundException {
		LOGGER.debug("request for updating shipping address for userId: {}", userId);
		return shippingService.updateShippingAddress(userId, shippingAddress);
	}
}
