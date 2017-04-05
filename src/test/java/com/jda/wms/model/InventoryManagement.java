package com.jda.wms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "type", "sellerMaliId", "sellerPassword", "Category1", "Category2", "Category3", "Category4",
		"productSKU", "sefetyStock", "inventoryStock" })
public class InventoryManagement {
	@JsonProperty("type")
	private String type;
	@JsonProperty("sellerMaliId")
	private String sellerMaliId;
	@JsonProperty("sellerPassword")
	private String sellerPassword;
	@JsonProperty("Category1")
	private String Category1;
	@JsonProperty("Category2")
	private String Category2;
	@JsonProperty("Category3")
	private String Category3;
	@JsonProperty("Category4")
	private String Category4;
	@JsonProperty("productSKU")
	private String productSKU;
	@JsonProperty("sefetyStock")
	private String sefetyStock;
	@JsonProperty("inventoryStock")
	private String inventoryStock;

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("sellerMaliId")
	public String getSellerMaliId() {
		return sellerMaliId;
	}

	@JsonProperty("sellerMaliId")
	public void setSellerMaliId(String sellerMaliId) {
		this.sellerMaliId = sellerMaliId;
	}

	@JsonProperty("sellerPassword")
	public String getSellerPassword() {
		return sellerPassword;
	}

	@JsonProperty("sellerPassword")
	public void setSellerPassword(String sellerPassword) {
		this.sellerPassword = sellerPassword;
	}

	@JsonProperty("Category1")
	public String getCategory1() {
		return Category1;
	}

	@JsonProperty("Category1")
	public void setCategory1(String Category1) {
		this.Category1 = Category1;
	}

	@JsonProperty("Category2")
	public String getCategory2() {
		return Category2;
	}

	@JsonProperty("Category2")
	public void setCategory2(String Category2) {
		this.Category2 = Category2;
	}

	@JsonProperty("Category3")
	public String getCategory3() {
		return Category3;
	}

	@JsonProperty("Category3")
	public void setCategory3(String Category3) {
		this.Category3 = Category3;
	}

	@JsonProperty("Category4")
	public String getCategory4() {
		return Category4;
	}

	@JsonProperty("Category4")
	public void setCategory4(String Category4) {
		this.Category4 = Category4;
	}

	@JsonProperty("productSKU")
	public String getProductSKU() {
		return productSKU;
	}

	@JsonProperty("productSKU")
	public void setProductSKU(String productSKU) {
		this.productSKU = productSKU;
	}

	@JsonProperty("sefetyStock")
	public String getSefetyStock() {
		return sefetyStock;
	}

	@JsonProperty("sefetyStock")
	public void setSefetyStock(String sefetyStock) {
		this.sefetyStock = sefetyStock;
	}

	@JsonProperty("inventoryStock")
	public String getInventoryStock() {
		return inventoryStock;
	}

	@JsonProperty("inventoryStock")
	public void setInventoryStock(String inventoryStock) {
		this.inventoryStock = inventoryStock;
	}

	@Override
	public String toString() {
		return type + "-" + sellerMaliId + "-" + sellerPassword + "-" + productSKU + "-" + sefetyStock + "-"
				+ inventoryStock;
	}
}
