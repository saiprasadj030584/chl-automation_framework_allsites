package com.jda.wms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"type",
"productName",
"pincode"
})
public class Product {

@JsonProperty("type")
private String type;
@JsonProperty("productName")
private String productName;
@JsonProperty("pincode")
private String pincode;

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

@JsonProperty("pincode")
public String getPincode() {
return pincode;
}

@JsonProperty("pincode")
public void setPincode(String pincode) {
this.pincode = pincode;
}

}