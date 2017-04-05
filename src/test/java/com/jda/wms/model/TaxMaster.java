package com.jda.wms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type","state","category","vat","cst","taxactname"})
public class TaxMaster {
@JsonProperty("type")
private String type;
@JsonProperty("state")
private String state;
@JsonProperty("category")
private String category;
@JsonProperty("vat")
private String vat;
@JsonProperty("cst")
private String cst;
@JsonProperty("taxactname")
private String taxactname;


@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

@JsonProperty("state")
public String getState() {
return state;
}

@JsonProperty("state")
public void setState(String state) {
this.state = state;
}

@JsonProperty("category")
public String getCategory() {
return category;
}

@JsonProperty("category")
public void setCategory(String category) {
this.category = category;
}

@JsonProperty("vat")
public String getVat() {
return vat;
}

@JsonProperty("vat")
public void setVat(String vat) {
this.vat = vat;
}

@JsonProperty("cst")
public String getCst() {
return cst;
}

@JsonProperty("cst")
public void setCst(String cst) {
this.cst = cst;
}

@JsonProperty("taxactname")
public String getTaxactname() {
return taxactname;
}

@JsonProperty("taxactname")
public void setTaxactname(String taxactname) {
this.taxactname = taxactname;
}

}

