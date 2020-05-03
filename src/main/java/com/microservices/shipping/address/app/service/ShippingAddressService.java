package com.microservices.shipping.address.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.shipping.address.app.entity.ShippingAddress;
import com.microservices.shipping.address.app.entity.User;
import com.microservices.shipping.address.app.exception.ShippingAddressNotFoundException;
import com.microservices.shipping.address.app.exception.UserNotFoundException;
import com.microservices.shipping.address.app.repository.ShippingAddressRepository;

@Service
public class ShippingAddressService {

	private static Logger LOGGER = LoggerFactory.getLogger(ShippingAddressService.class);
	
	@Autowired
	private ShippingAddressRepository shippinAddressRepo;

	public List<ShippingAddress> getShippingAddressListByUserId(String userId) throws UserNotFoundException {
		checkUserValidity(userId);
		Iterable<ShippingAddress> addressList = shippinAddressRepo.findAll();
		return StreamSupport.stream(addressList.spliterator(), false)
				.filter(shippingAddress -> StringUtils.equals(String.valueOf(shippingAddress.getUserId()), userId))
				.collect(Collectors.toList());
	}

	public ShippingAddress getShippingAddressById(String userId, String addressId)
			throws ShippingAddressNotFoundException, UserNotFoundException {
		checkUserValidity(userId);
		ShippingAddress shippingAddress = shippinAddressRepo.findById(Long.valueOf(addressId)).get();
		if (!StringUtils.equals(userId, String.valueOf(shippingAddress.getUserId()))) {
			LOGGER.error("Unable to find the Shipping Address with addressId: {}", addressId);
			throw new ShippingAddressNotFoundException("Shipping Address is not found in DB");
		}
		LOGGER.debug("shipping address found with addressId: {}",addressId);
		return shippingAddress;
	}

	public ResponseEntity<ShippingAddress> createShippingAddress(String userId, ShippingAddress newShippingAddress)
			throws UserNotFoundException {
		checkUserValidity(userId);
		newShippingAddress.setUserId(Long.valueOf(userId));
		Long addressId = generateUniqueId();
		newShippingAddress.setAddressId(addressId);
		ShippingAddress shippingAddress = shippinAddressRepo.save(newShippingAddress);
		LOGGER.debug("shipping address created with addressId: {}",addressId);
		return new ResponseEntity(shippingAddress, HttpStatus.CREATED);
	}

	public void deleteShippingAddress(String userId, String shippingAddressId) throws UserNotFoundException {
		checkUserValidity(userId);
		shippinAddressRepo.deleteById(Long.valueOf(shippingAddressId));
		LOGGER.debug("shipping address deleted successfully");
	}

	public ResponseEntity<ShippingAddress> updateShippingAddress(String userId, ShippingAddress shippingAddress)
			throws UserNotFoundException {
		checkUserValidity(userId);
		shippinAddressRepo.save(shippingAddress);
		LOGGER.debug("shipping address updated successfully");
		return new ResponseEntity(shippingAddress, HttpStatus.OK);
	}

	private Long generateUniqueId() {
		long uniqueshippinAddressId = -1;
		do {
			uniqueshippinAddressId = UUID.randomUUID().getMostSignificantBits();
		} while (uniqueshippinAddressId < 0);
		String shippinAddressId = String.valueOf(uniqueshippinAddressId);
		uniqueshippinAddressId = Long.valueOf(shippinAddressId.substring(0, 5));
		return uniqueshippinAddressId;

	}

	private User getUserFromRestApi(String userId) {
		final String uri = "http://localhost:8100/user/{userId}";
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("userId", userId);
		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForObject(uri, User.class, uriVariables);
		return user;
	}

	private void checkUserValidity(String userId) throws UserNotFoundException {
		User user = getUserFromRestApi(userId);
		if (null == user.getUserId()) {
			LOGGER.error("Unable to find the user with userId: {}", userId);
			throw new UserNotFoundException("User ID is invalid or not found in DB");
		}
	}
}
