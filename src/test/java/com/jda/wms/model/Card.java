package com.jda.wms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "type", "cardType", "cardNumber", "cardName", "expiryMonth", "expiryYear", "cvv" })
public class Card {

	@JsonProperty("type")
	private String type;
	@JsonProperty("cardType")
	private String cardType;
	@JsonProperty("cardNumber")
	private String cardNumber;
	@JsonProperty("cardName")
	private String cardName;
	@JsonProperty("expiryMonth")
	private String expiryMonth;
	@JsonProperty("expiryYear")
	private String expiryYear;
	@JsonProperty("cvv")
	private String cvv;

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("cardType")
	public String getCardType() {
		return cardType;
	}

	@JsonProperty("cardType")
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@JsonProperty("cardNumber")
	public String getCardNumber() {
		return cardNumber;
	}

	@JsonProperty("cardNumber")
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@JsonProperty("cardName")
	public String getCardName() {
		return cardName;
	}

	@JsonProperty("cardName")
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	@JsonProperty("expiryMonth")
	public String getExpiryMonth() {
		return expiryMonth;
	}

	@JsonProperty("expiryMonth")
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	@JsonProperty("expiryYear")
	public String getExpiryYear() {
		return expiryYear;
	}

	@JsonProperty("expiryYear")
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}

	@JsonProperty("cvv")
	public String getCvv() {
		return cvv;
	}

	@JsonProperty("cvv")
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	@Override
	public String toString() {
		return type + "-" + cardType + "-" + cardNumber + "-" + cardName + "-" + expiryMonth + "-" + expiryYear + "-"
				+ cvv;
	}

}