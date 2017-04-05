package com.jda.wms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "type", "sellerMailId", "sellerPassword", "sKUID", "storeCode", "productStock", "safetyStock" })
public class InventoryDetail {
	@JsonProperty("type")
	private String type;
	@JsonProperty("sKUID")
	private String sKUID;
	@JsonProperty("sellerMailId")
	private String sellerMailId;
	@JsonProperty("sellerPassword")
	private String sellerPassword;
	@JsonProperty("storeCode")
	private String storeCode;
	@JsonProperty("productStock")
	private String productStock;
	@JsonProperty("safetyStock")
	private String safetyStock;

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("sKUID")
	public String getSKUID() {
		return sKUID;
	}

	@JsonProperty("sKUID")
	public void setSKUID(String sKUID) {
		this.sKUID = sKUID;
	}

	@JsonProperty("sellerMailId")
	public String getSellerMailId() {
		return sellerMailId;
	}

	@JsonProperty("sellerMailId")
	public void setSellerMailId(String sellerMailId) {
		this.sellerMailId = sellerMailId;
	}

	@JsonProperty("sellerPassword")
	public String getSellerPassword() {
		return sellerPassword;
	}

	@JsonProperty("sellerPassword")
	public void setSellerPassword(String sellerPassword) {
		this.sellerPassword = sellerPassword;
	}

	@JsonProperty("storeCode")
	public String getStoreCode() {
		return storeCode;
	}

	@JsonProperty("storeCode")
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	@JsonProperty("productStock")
	public String getProductStock() {
		return productStock;
	}

	@JsonProperty("productStock")
	public void setProductStock(String productStock) {
		this.productStock = productStock;
	}

	@JsonProperty("safetyStock")
	public String getSafetyStock() {
		return safetyStock;
	}

	@JsonProperty("safetyStock")
	public void setSafetyStock(String safetyStock) {
		this.safetyStock = safetyStock;
	}
}
