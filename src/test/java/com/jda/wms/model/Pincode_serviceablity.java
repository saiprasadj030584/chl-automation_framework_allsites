package com.jda.wms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "type", "pincodes", "state", "country", "city", "slavepriority", "sellercode", "pinCode",
		"stateCode", "countryName", "placeName", "slavePriority", "tatCode", "tataPriority" })
public class Pincode_serviceablity {
	@JsonProperty("type")
	private String type;
	@JsonProperty("pincodes")
	private String pincodes;
	@JsonProperty("state")
	private String state;
	@JsonProperty("country")
	private String country;
	@JsonProperty("city")
	private String city;
	@JsonProperty("slavepriority")
	private String slavepriority;
	@JsonProperty("sellercode")
	private String sellercode;
	@JsonProperty("pinCode")
	private String pinCode;
	@JsonProperty("stateCode")
	private String stateCode;
	@JsonProperty("countryName")
	private String countryName;
	@JsonProperty("placeName")
	private String placeName;
	@JsonProperty("slavePriority")
	private String slavePriority;
	@JsonProperty("tatCode")
	private String tatCode;
	@JsonProperty("tataPriority")
	private String tataPriority;

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("pincodes")
	public String getPincodes() {
		return pincodes;
	}

	@JsonProperty("pincodes")
	public void setPincodes(String pincodes) {
		this.pincodes = pincodes;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty("country")
	public String getCountry() {
		return country;
	}

	@JsonProperty("country")
	public void setCountry(String country) {
		this.country = country;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("slavepriority")
	public String getSlavepriority() {
		return slavepriority;
	}

	@JsonProperty("slavepriority")
	public void setSlavepriority(String slavepriority) {
		this.slavepriority = slavepriority;
	}

	@JsonProperty("sellercode")
	public String getSellercode() {
		return sellercode;
	}

	@JsonProperty("sellercode")
	public void setSellercode(String sellercode) {
		this.sellercode = sellercode;
	}

	@JsonProperty("pinCode")
	public String getPinCode() {
		return pinCode;
	}

	@JsonProperty("pinCode")
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	@JsonProperty("stateCode")
	public String getStateCode() {
		return stateCode;
	}

	@JsonProperty("stateCode")
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@JsonProperty("countryName")
	public String getCountryName() {
		return countryName;
	}

	@JsonProperty("countryName")
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@JsonProperty("placeName")
	public String getPlaceName() {
		return placeName;
	}

	@JsonProperty("placeName")
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	@JsonProperty("slavePriority")
	public String getSlavePriority() {
		return slavePriority;
	}

	@JsonProperty("slavePriority")
	public void setSlavePriority(String slavePriority) {
		this.slavePriority = slavePriority;
	}

	@JsonProperty("tatCode")
	public String getTatCode() {
		return tatCode;
	}

	@JsonProperty("tatCode")
	public void setTatCode(String tatCode) {
		this.tatCode = tatCode;
	}

	@JsonProperty("tataPriority")
	public String getTataPriority() {
		return tataPriority;
	}

	@JsonProperty("tataPriority")
	public void setTataPriority(String tataPriority) {
		this.tataPriority = tataPriority;
	}
}