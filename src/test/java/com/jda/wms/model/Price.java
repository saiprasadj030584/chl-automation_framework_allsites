package com.jda.wms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "type", "productName", "password", "category1", "category2", "category3", "category4", "mrp",
		"offer" })
public class Price {
	@JsonProperty("type")
	private String type;
	@JsonProperty("productName")
	private String productName;
	@JsonProperty("password")
	private String password;
	@JsonProperty("category1")
	private String category1;
	@JsonProperty("category2")
	private String category2;
	@JsonProperty("category3")
	private String category3;
	@JsonProperty("category4")
	private String category4;
	@JsonProperty("mrp")
	private String mrp;
	@JsonProperty("offer")
	private String offer;

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("productName")
	public String getProductName() {
		return productName;
	}

	@JsonProperty("productName")
	public void setProductName(String productName) {
		this.productName = productName;
	}

	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	@JsonProperty("productName")
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonProperty("category1")
	public String getCategory1() {
		return category1;
	}

	@JsonProperty("category1")
	public void setCategory1(String category1) {
		this.category1 = category1;
	}

	@JsonProperty("category2")
	public String getCategory2() {
		return category2;
	}

	@JsonProperty("category2")
	public void setCategory2(String category2) {
		this.category2 = category2;
	}

	@JsonProperty("category3")
	public String getCategory3() {
		return category3;
	}

	@JsonProperty("category3")
	public void setCategory3(String category3) {
		this.category3 = category3;
	}

	@JsonProperty("category4")
	public String getCategory4() {
		return category4;
	}

	@JsonProperty("category4")
	public void setCategory4(String category4) {
		this.category4 = category4;
	}

	@JsonProperty("mrp")
	public String getMrp() {
		return mrp;
	}

	@JsonProperty("mrp")
	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	@JsonProperty("offer")
	public String getOffer() {
		return offer;
	}

	@JsonProperty("offer")
	public void setOffer(String offer) {
		this.offer = offer;
	}

}
