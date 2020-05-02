package com.microservices.shipping.address.app.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("address")
public class ShippingAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1629564097137669707L;
	private Long userId;
	private @Id Long addressId;
	private Integer pinCode;
	private String area;
	private String district;
	private String state;
	private String country;
	private boolean defaultAddress;

	protected ShippingAddress() {
	}

	/**
	 * @param pinCode
	 * @param area
	 * @param district
	 * @param state
	 * @param country
	 * @param defaultAddress
	 * @param user
	 */
	public ShippingAddress(Long userId, Long addressId, Integer pinCode, String area, String district, String state,
			String country, boolean defaultAddress) {
		super();
		this.userId = userId;
		this.addressId = addressId;
		this.pinCode = pinCode;
		this.area = area;
		this.district = district;
		this.state = state;
		this.country = country;
		this.defaultAddress = defaultAddress;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the addressId
	 */
	public Long getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the pinCode
	 */
	public Integer getPinCode() {
		return pinCode;
	}

	/**
	 * @param pinCode the pinCode to set
	 */
	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the defaultAddress
	 */
	public boolean isDefaultAddress() {
		return defaultAddress;
	}

	/**
	 * @param defaultAddress the defaultAddress to set
	 */
	public void setDefaultAddress(boolean defaultAddress) {
		this.defaultAddress = defaultAddress;
	}

	@Override
	public String toString() {
		return "ShippingAddress [userId=" + userId + ", addressId=" + addressId + ", pinCode=" + pinCode + ", area="
				+ area + ", district=" + district + ", state=" + state + ", country=" + country + ", defaultAddress="
				+ defaultAddress + "]";
	}
}
