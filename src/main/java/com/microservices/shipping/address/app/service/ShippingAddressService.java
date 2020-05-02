package com.microservices.shipping.address.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang.StringUtils;
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

	@Autowired
	private ShippingAddressRepository shippinAddressRepo;

	public List<ShippingAddress> getShippingAddressListByUserId(String userId) throws UserNotFoundException {
		User user = getUserFromRestApi(userId);
		if (null == user.getUserId())
			throw new UserNotFoundException("User ID is invalid or not found in DB");
		Iterable<ShippingAddress> addressList = shippinAddressRepo.findAll();
		return StreamSupport.stream(addressList.spliterator(), false)
				.filter(shippingAddress -> StringUtils.equals(String.valueOf(shippingAddress.getUserId()), userId))
				.collect(Collectors.toList());
	}

	public ShippingAddress getShippingAddressById(String userId, String addressId)
			throws ShippingAddressNotFoundException, UserNotFoundException {
		User user = getUserFromRestApi(userId);
		if (null == user.getUserId())
			throw new UserNotFoundException("User ID is invalid or not found in DB");
		ShippingAddress shippingAddress = shippinAddressRepo.findById(Long.valueOf(addressId)).get();
		if (!StringUtils.equals(userId, String.valueOf(shippingAddress.getUserId())))
			throw new ShippingAddressNotFoundException("Shipping Address is not found in DB");
		return shippingAddress;
	}

	public ResponseEntity<ShippingAddress> createShippingAddress(String userId, ShippingAddress newShippingAddress)
			throws UserNotFoundException {
		User user = getUserFromRestApi(userId);
		if (null == user.getUserId())
			throw new UserNotFoundException("User ID is invalid or not found in DB");
		newShippingAddress.setUserId(Long.valueOf(userId));
		newShippingAddress.setAddressId(generateUniqueId());
		ShippingAddress shippingAddress = shippinAddressRepo.save(newShippingAddress);
		return new ResponseEntity(shippingAddress, HttpStatus.CREATED);
	}

	public void deleteShippingAddress(String userId, String shippingAddressId) throws UserNotFoundException {
		User user = getUserFromRestApi(userId);
		if (null == user.getUserId())
			throw new UserNotFoundException("User ID is invalid or not found in DB");
		shippinAddressRepo.deleteById(Long.valueOf(shippingAddressId));
	}

	public ResponseEntity<ShippingAddress> updateShippingAddress(String userId, ShippingAddress shippingAddress)
			throws UserNotFoundException {
		User user = getUserFromRestApi(userId);
		if (null == user.getUserId())
			throw new UserNotFoundException("User ID is invalid or not found in DB");
		shippinAddressRepo.save(shippingAddress);
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

}
