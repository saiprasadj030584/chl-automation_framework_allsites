package com.jda.wms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class SellerId {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({ "type", "selleridnum" })

	@JsonProperty("type")
	private String type;
	@JsonProperty("selleridnum")
	private String selleridnum;

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("selleridnum")
	public String getSelleridnum() {
		return selleridnum;
	}

	@JsonProperty("selleridnum")
	public void setSelleridnum(String selleridnum) {
		this.selleridnum = selleridnum;
	}

}
