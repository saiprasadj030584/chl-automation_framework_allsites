package com.jda.wms.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "type", "pincodes", "country", "state", "city", "transportmode", "prepaidlimit", "codlimit",
		"codavailable", "isformrequired", "returnflag" })
public class MasterPincode {

	@JsonProperty("type")
	private String type;
	@JsonProperty("pincodes")
	private String pincodes;
	@JsonProperty("country")
	private String country;
	@JsonProperty("state")
	private String state;
	@JsonProperty("city")
	private String city;
	@JsonProperty("transportmode")
	private String transportmode;
	@JsonProperty("prepaidlimit")
	private String prepaidlimit;
	@JsonProperty("codlimit")
	private String codlimit;
	@JsonProperty("codavailable")
	private String codavailable;
	@JsonProperty("isformrequired")
	private String isformrequired;
	@JsonProperty("returnflag")
	private String returnflag;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

	@JsonProperty("country")
	public String getCountry() {
		return country;
	}

	@JsonProperty("country")
	public void setCountry(String country) {
		this.country = country;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("transportmode")
	public String getTransportmode() {
		return transportmode;
	}

	@JsonProperty("transportmode")
	public void setTransportmode(String transportmode) {
		this.transportmode = transportmode;
	}

	@JsonProperty("prepaidlimit")
	public String getPrepaidlimit() {
		return prepaidlimit;
	}

	@JsonProperty("prepaidlimit")
	public void setPrepaidlimit(String prepaidlimit) {
		this.prepaidlimit = prepaidlimit;
	}

	@JsonProperty("codlimit")
	public String getCodlimit() {
		return codlimit;
	}

	@JsonProperty("codlimit")
	public void setCodlimit(String codlimit) {
		this.codlimit = codlimit;
	}

	@JsonProperty("codavailable")
	public String getCodavailable() {
		return codavailable;
	}

	@JsonProperty("codavailable")
	public void setCodavailable(String codavailable) {
		this.codavailable = codavailable;
	}

	@JsonProperty("isformrequired")
	public String getIsformrequired() {
		return isformrequired;
	}

	@JsonProperty("isformrequired")
	public void setIsformrequired(String isformrequired) {
		this.isformrequired = isformrequired;
	}

	@JsonProperty("returnflag")
	public String getReturnflag() {
		return returnflag;
	}

	@JsonProperty("returnflag")
	public void setReturnflag(String returnflag) {
		this.returnflag = returnflag;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
