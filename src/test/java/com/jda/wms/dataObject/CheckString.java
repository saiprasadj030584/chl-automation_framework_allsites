package com.jda.wms.dataObject;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckString {

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