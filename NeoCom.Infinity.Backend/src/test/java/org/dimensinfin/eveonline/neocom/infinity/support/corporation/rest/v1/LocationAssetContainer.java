package org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1;

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
		"spaceLocation",
		"containerType",
		"contents"
})
public class LocationAssetContainer {

	@JsonProperty("jsonClass")
	private String jsonClass;
	@JsonProperty("spaceLocation")
	private SpaceLocationJson spaceLocationJson;
	@JsonProperty("containerType")
	private String containerType;
	@JsonProperty("contents")
	private List<NeoAssetAssetContainerJson> contents = null;
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

	@JsonProperty("spaceLocation")
	public SpaceLocationJson getSpaceLocationJson() {
		return spaceLocationJson;
	}

	@JsonProperty("spaceLocation")
	public void setSpaceLocationJson( SpaceLocationJson spaceLocationJson ) {
		this.spaceLocationJson = spaceLocationJson;
	}

	@JsonProperty("containerType")
	public String getContainerType() {
		return containerType;
	}

	@JsonProperty("containerType")
	public void setContainerType( String containerType ) {
		this.containerType = containerType;
	}

	@JsonProperty("contents")
	public List<NeoAssetAssetContainerJson> getContents() {
		return contents;
	}

	@JsonProperty("contents")
	public void setContents( List<NeoAssetAssetContainerJson> contents ) {
		this.contents = contents;
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
