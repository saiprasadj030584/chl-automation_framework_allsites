package com.jda.wms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "type", "sellerMaliId", "sellerPassword", "parentSlaveERPcode", "parentSlaveERPname",
		"feederInformation" })
public class ParentFeederBulkUpload {
	@JsonProperty("type")
	private String type;
	@JsonProperty("sellerMaliId")
	private String sellerMaliId;
	@JsonProperty("sellerPassword")
	private String sellerPassword;
	@JsonProperty("parentSlaveERPcode")
	private String parentSlaveERPcode;
	@JsonProperty("parentSlaveERPname")
	private String parentSlaveERPname;
	@JsonProperty("feederInformation")
	private String feederInformation;

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

	@JsonProperty("parentSlaveERPcode")
	public String getParentSlaveERPcode() {
		return parentSlaveERPcode;
	}

	@JsonProperty("parentSlaveERPcode")
	public void setParentSlaveERPcode(String parentSlaveERPcode) {
		this.parentSlaveERPcode = parentSlaveERPcode;
	}

	@JsonProperty("parentSlaveERPname")
	public String getParentSlaveERPname() {
		return parentSlaveERPname;
	}

	@JsonProperty("parentSlaveERPname")
	public void setParentSlaveERPname(String parentSlaveERPname) {
		this.parentSlaveERPname = parentSlaveERPname;
	}

	@JsonProperty("feederInformation")
	public String getFeederInformation() {
		return feederInformation;
	}

	@JsonProperty("feederInformation")
	public void setFeederInformation(String feederInformation) {
		this.feederInformation = feederInformation;
	}
}
