package com.jda.wms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "type", "productListingId", "pinCode", "firstName", "lastName", "addressLine1", "addressLine2",
		"landmark", "cityName", "postalCode", "mobileNumber" })
public class Order_through_cscockpit {
	@JsonProperty("type")
	private String type;
	@JsonProperty("productListingId")
	private String productListingId;
	@JsonProperty("pinCode")
	private String pinCode;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("addressLine1")
	private String addressLine1;
	@JsonProperty("addressLine2")
	private String addressLine2;
	@JsonProperty("landmark")
	private String landmark;
	@JsonProperty("cityName")
	private String cityName;
	@JsonProperty("postalCode")
	private String postalCode;
	@JsonProperty("mobileNumber")
	private String mobileNumber;

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("productListingId")
	public String getProductListingId() {
		return productListingId;
	}

	@JsonProperty("productListingId")
	public void setProductListingId(String productListingId) {
		this.productListingId = productListingId;
	}

	@JsonProperty("pinCode")
	public String getPinCodes() {
		return pinCode;
	}

	@JsonProperty("pinCode")
	public void setPinCodes(String pinCodes) {
		this.pinCode = pinCodes;
	}

	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("addressLine1")
	public String getAddressLine1() {
		return addressLine1;
	}

	@JsonProperty("addressLine1")
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	@JsonProperty("addressLine2")
	public String getAddressLine2() {
		return addressLine2;
	}

	@JsonProperty("addressLine2")
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	@JsonProperty("landmark")
	public String getLandmark() {
		return landmark;
	}

	@JsonProperty("landmark")
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	@JsonProperty("cityName")
	public String getCityName() {
		return cityName;
	}

	@JsonProperty("cityName")
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@JsonProperty("postalCode")
	public String getPostalCode() {
		return postalCode;
	}

	@JsonProperty("postalCode")
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@JsonProperty("mobileNumber")
	public String getMobileNumber() {
		return mobileNumber;
	}

	@JsonProperty("mobileNumber")
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
}
