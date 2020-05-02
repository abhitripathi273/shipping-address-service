package com.microservices.shipping.address.app.controller;

import java.util.List;

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

	@Autowired
	private ShippingAddressService shippingService;

	@GetMapping("/user/{userId}/shippingAddressList")
	public List<ShippingAddress> getShippingAddressList(@PathVariable String userId) throws UserNotFoundException {
		return shippingService.getShippingAddressListByUserId(userId);
	}

	@GetMapping("/user/{userId}/shippingAddress/{addressId}")
	public ShippingAddress getShippingAddressById(@PathVariable String userId, @PathVariable String addressId)
			throws ShippingAddressNotFoundException, UserNotFoundException {
		return shippingService.getShippingAddressById(userId, addressId);
	}

	@PostMapping("/user/{userId}/shippingAddress")
	@ResponseStatus
	public ResponseEntity<ShippingAddress> createShippingAddress(@PathVariable String userId,
			@RequestBody ShippingAddress shippingAddress) throws UserNotFoundException {
		return shippingService.createShippingAddress(userId, shippingAddress);
	}

	@DeleteMapping("/user/{userId}/shippingAddress/{addressId}")
	public void deleteShippingAddress(@PathVariable String userId, @PathVariable String addressId)
			throws UserNotFoundException {
		shippingService.deleteShippingAddress(userId, addressId);
	}

	@PutMapping("/user/{userId}/shippingAddress")
	@ResponseStatus
	public ResponseEntity<ShippingAddress> updateShippingAddress(@PathVariable String userId,
			@RequestBody ShippingAddress shippingAddress) throws UserNotFoundException {
		return shippingService.updateShippingAddress(userId, shippingAddress);
	}
}
