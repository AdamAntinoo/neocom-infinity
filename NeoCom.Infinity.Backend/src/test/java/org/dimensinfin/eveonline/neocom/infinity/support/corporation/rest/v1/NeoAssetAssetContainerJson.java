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
		"face",
		"containerType",
		"contents"
})
public class NeoAssetAssetContainerJson {

	@JsonProperty("jsonClass")
	private String jsonClass;
	@JsonProperty("face")
	private NeoAssetJson neoAssetJson;
	@JsonProperty("containerType")
	private String containerType;
	@JsonProperty("contents")
	private List<NeoAssetJson> contents = null;
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

	@JsonProperty("face")
	public NeoAssetJson getNeoAssetJson() {
		return neoAssetJson;
	}

	@JsonProperty("face")
	public void setNeoAssetJson( NeoAssetJson neoAssetJson ) {
		this.neoAssetJson = neoAssetJson;
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
	public List<NeoAssetJson> getContents() {
		return contents;
	}

	@JsonProperty("contents")
	public void setContents( List<NeoAssetJson> contents ) {
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
