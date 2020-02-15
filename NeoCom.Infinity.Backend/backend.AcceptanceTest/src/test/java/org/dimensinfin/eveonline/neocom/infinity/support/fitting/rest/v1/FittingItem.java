package org.dimensinfin.eveonline.neocom.infinity.support.fitting.rest.v1;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"jsonClass",
		"typeId",
		"name",
		"location"
})
public class FittingItem {
	@JsonProperty("jsonClass")
	private String jsonClass;
	@JsonProperty("typeId")
	private Integer typeId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("location")
	private String location;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("jsonClass")
	public String getJsonClass() {
		return jsonClass;
	}

	@JsonProperty("jsonClass")
	public void setJsonClass( String jsonClass ) {
		this.jsonClass = jsonClass;
	}

	@JsonProperty("typeId")
	public Integer getTypeId() {
		return typeId;
	}

	@JsonProperty("typeId")
	public void setTypeId( Integer typeId ) {
		this.typeId = typeId;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName( String name ) {
		this.name = name;
	}

	@JsonProperty("location")
	public String getLocation() {
		return location;
	}

	@JsonProperty("location")
	public void setLocation( String location ) {
		this.location = location;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty( String name, Object value ) {
		this.additionalProperties.put( name, value );
	}
}
