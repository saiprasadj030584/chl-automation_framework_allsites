package com.jda.wms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "type", "productName", "passWord", "priceMRP", "priceMOP", "homeDelivery", "expressDelivery",
		"clickAndCollect" })
public class PriceDetail {

	@JsonProperty("type")
	private String type;
	@JsonProperty("productName")
	private String productName;
	@JsonProperty("passWord")
	private String passWord;
	@JsonProperty("priceMRP")
	private String priceMRP;
	@JsonProperty("priceMOP")
	private String priceMOP;
	@JsonProperty("homeDelivery")
	private String homeDelivery;
	@JsonProperty("expressDelivery")
	private String expressDelivery;
	@JsonProperty("clickAndCollect")
	private String clickAndCollect;

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

	@JsonProperty("passWord")
	public String getPassWord() {
		return passWord;
	}

	@JsonProperty("passWord")
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@JsonProperty("priceMRP")
	public String getPriceMRP() {
		return priceMRP;
	}

	@JsonProperty("priceMRP")
	public void setPriceMRP(String priceMRP) {
		this.priceMRP = priceMRP;
	}

	@JsonProperty("priceMOP")
	public String getPriceMOP() {
		return priceMOP;
	}

	@JsonProperty("priceMOP")
	public void setPriceMOP(String priceMOP) {
		this.priceMOP = priceMOP;
	}

	@JsonProperty("homeDelivery")
	public String getHomeDelivery() {
		return homeDelivery;
	}

	@JsonProperty("homeDelivery")
	public void setHomeDelivery(String homeDelivery) {
		this.homeDelivery = homeDelivery;
	}

	@JsonProperty("expressDelivery")
	public String getExpressDelivery() {
		return expressDelivery;
	}

	@JsonProperty("expressDelivery")
	public void setExpressDelivery(String expressDelivery) {
		this.expressDelivery = expressDelivery;
	}

	@JsonProperty("clickAndCollect")
	public String getClickAndCollect() {
		return clickAndCollect;
	}

	@JsonProperty("clickAndCollect")
	public void setClickAndCollect(String clickAndCollect) {
		this.clickAndCollect = clickAndCollect;
	}

}
