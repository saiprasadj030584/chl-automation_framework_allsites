package com.jda.wms.dataObject;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "checkString" })
public class CheckString {

	@JsonProperty("checkString")
	private List<String> checkString = null;

	@JsonProperty("checkString")
	public List<String> getCheckString() {
		return checkString;
	}

	@JsonProperty("checkString")
	public void setCheckString(List<String> checkString) {
		this.checkString = checkString;
	}

}
