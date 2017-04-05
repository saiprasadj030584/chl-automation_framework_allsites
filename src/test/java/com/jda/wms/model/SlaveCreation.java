package com.jda.wms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "type", "sellerMailId", "firstName", "lastName", "landlineNumber", "mobileNumber", "latitude",
		"longitude", "cstNumber", "ServiceTaxnumber", "vatNumber", "slaveAddress1", "slaveAddress2", "pinCode",
		"officePhoneNumber" })
public class SlaveCreation {

	@JsonProperty("type")
	private String type;
	@JsonProperty("sellerMailId")
	private String sellerMailId;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("landlineNumber")
	private String landlineNumber;
	@JsonProperty("mobileNumber")
	private String mobileNumber;
	@JsonProperty("latitude")
	private String latitude;
	@JsonProperty("longitude")
	private String longitude;
	@JsonProperty("cstNumber")
	private String cstNumber;
	@JsonProperty("ServiceTaxnumber")
	private String serviceTaxnumber;
	@JsonProperty("vatNumber")
	private String vatNumber;
	@JsonProperty("slaveAddress1")
	private String slaveAddress1;
	@JsonProperty("slaveAddress2")
	private String slaveAddress2;
	@JsonProperty("pinCode")
	private String pinCode;
	@JsonProperty("officePhoneNumber")
	private String officePhoneNumber;

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("sellerMailId")
	public String getSellerMailId() {
		return sellerMailId;
	}

	@JsonProperty("sellerMailId")
	public void setSellerMailId(String sellerMailId) {
		this.sellerMailId = sellerMailId;
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

	@JsonProperty("landlineNumber")
	public String getLandlineNumber() {
		return landlineNumber;
	}

	@JsonProperty("landlineNumber")
	public void setLandlineNumber(String landlineNumber) {
		this.landlineNumber = landlineNumber;
	}

	@JsonProperty("mobileNumber")
	public String getMobileNumber() {
		return mobileNumber;
	}

	@JsonProperty("mobileNumber")
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@JsonProperty("latitude")
	public String getLatitude() {
		return latitude;
	}

	@JsonProperty("latitude")
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@JsonProperty("longitude")
	public String getLongitude() {
		return longitude;
	}

	@JsonProperty("longitude")
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@JsonProperty("cstNumber")
	public String getCstNumber() {
		return cstNumber;
	}

	@JsonProperty("cstNumber")
	public void setCstNumber(String cstNumber) {
		this.cstNumber = cstNumber;
	}

	@JsonProperty("ServiceTaxnumber")
	public String getServiceTaxnumber() {
		return serviceTaxnumber;
	}

	@JsonProperty("ServiceTaxnumber")
	public void setServiceTaxnumber(String serviceTaxnumber) {
		this.serviceTaxnumber = serviceTaxnumber;
	}

	@JsonProperty("vatNumber")
	public String getVatNumber() {
		return vatNumber;
	}

	@JsonProperty("vatNumber")
	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	@JsonProperty("slaveAddress1")
	public String getSlaveAddress1() {
		return slaveAddress1;
	}

	@JsonProperty("slaveAddress1")
	public void setSlaveAddress1(String slaveAddress1) {
		this.slaveAddress1 = slaveAddress1;
	}

	@JsonProperty("slaveAddress2")
	public String getSlaveAddress2() {
		return slaveAddress2;
	}

	@JsonProperty("slaveAddress2")
	public void setSlaveAddress2(String slaveAddress2) {
		this.slaveAddress2 = slaveAddress2;
	}

	@JsonProperty("pinCode")
	public String getPinCode() {
		return pinCode;
	}

	@JsonProperty("pinCode")
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	@JsonProperty("officePhoneNumber")
	public String getOfficePhoneNumber() {
		return officePhoneNumber;
	}

	@JsonProperty("officePhoneNumber")
	public void setOfficePhoneNumber(String officePhoneNumber) {
		this.officePhoneNumber = officePhoneNumber;
	}

}