package com.microservices.shipping.address.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.microservices.shipping.address.app.entity.ShippingAddress;

public interface ShippingAddressRepository extends CrudRepository<ShippingAddress, Long> {

}
