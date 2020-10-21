package org.dimensinfin.eveonline.neocom.infinity.support.fitting.rest.v1;

import java.util.HashMap;
import java.util.List;
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
		"fittingId",
		"name",
		"description",
		"hullClass",
		"hullGroup",
		"hullIcon",
		"items"
})
public class FittingResponse {
	@JsonProperty("jsonClass")
	private String jsonClass;
	@JsonProperty("fittingId")
	private Integer fittingId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("description")
	private String description;
	@JsonProperty("hullClass")
	private String hullClass;
	@JsonProperty("hullGroup")
	private String hullGroup;
	@JsonProperty("hullIcon")
	private String hullIcon;
	@JsonProperty("items")
	private List<FittingItem> items = null;
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

	@JsonProperty("fittingId")
	public Integer getFittingId() {
		return fittingId;
	}

	@JsonProperty("fittingId")
	public void setFittingId( Integer fittingId ) {
		this.fittingId = fittingId;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName( String name ) {
		this.name = name;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription( String description ) {
		this.description = description;
	}

	@JsonProperty("hullClass")
	public String getHullClass() {
		return hullClass;
	}

	@JsonProperty("hullClass")
	public void setHullClass( String hullClass ) {
		this.hullClass = hullClass;
	}

	@JsonProperty("hullGroup")
	public String getHullGroup() {
		return hullGroup;
	}

	@JsonProperty("hullGroup")
	public void setHullGroup( String hullGroup ) {
		this.hullGroup = hullGroup;
	}

	@JsonProperty("hullIcon")
	public String getHullIcon() {
		return hullIcon;
	}

	@JsonProperty("hullIcon")
	public void setHullIcon( String hullIcon ) {
		this.hullIcon = hullIcon;
	}

	@JsonProperty("items")
	public List<FittingItem> getItems() {
		return items;
	}

	@JsonProperty("items")
	public void setItems( List<FittingItem> items ) {
		this.items = items;
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
