package com.jda.wms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"type",
"lpname"
})
public class LpName {

@JsonProperty("type")
private String type;
@JsonProperty("lpname")
private String lpname;

@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

@JsonProperty("lpname")
public String getLpName() {
return lpname;
}

@JsonProperty("lpname")
public void setLpName(String lpname) {
this.lpname = lpname;
}

}