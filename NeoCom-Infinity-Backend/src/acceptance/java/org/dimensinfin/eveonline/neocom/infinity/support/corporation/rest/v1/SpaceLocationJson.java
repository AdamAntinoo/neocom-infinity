package org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1;

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
		"locationId",
		"regionId",
		"regionName",
		"constellationId",
		"constellationName",
		"systemId",
		"systemName",
		"stationId",
		"stationName"
})
public class SpaceLocationJson {

	@JsonProperty("jsonClass")
	private String jsonClass;
	@JsonProperty("locationId")
	private Integer locationId;
	@JsonProperty("regionId")
	private Integer regionId;
	@JsonProperty("regionName")
	private String regionName;
	@JsonProperty("constellationId")
	private Integer constellationId;
	@JsonProperty("constellationName")
	private String constellationName;
	@JsonProperty("systemId")
	private Integer systemId;
	@JsonProperty("systemName")
	private String systemName;
	@JsonProperty("stationId")
	private Integer stationId;
	@JsonProperty("stationName")
	private String stationName;
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

	@JsonProperty("locationId")
	public Integer getLocationId() {
		return locationId;
	}

	@JsonProperty("locationId")
	public void setLocationId( Integer locationId ) {
		this.locationId = locationId;
	}

	@JsonProperty("regionId")
	public Integer getRegionId() {
		return regionId;
	}

	@JsonProperty("regionId")
	public void setRegionId( Integer regionId ) {
		this.regionId = regionId;
	}

	@JsonProperty("regionName")
	public String getRegionName() {
		return regionName;
	}

	@JsonProperty("regionName")
	public void setRegionName( String regionName ) {
		this.regionName = regionName;
	}

	@JsonProperty("constellationId")
	public Integer getConstellationId() {
		return constellationId;
	}

	@JsonProperty("constellationId")
	public SpaceLocationJson setConstellationId( final Integer constellationId ) {
		this.constellationId = constellationId;
		return this;
	}

	@JsonProperty("constellationName")
	public String getConstellationName() {
		return constellationName;
	}

	@JsonProperty("constellationName")
	public SpaceLocationJson setConstellationName( final String constellationName ) {
		this.constellationName = constellationName;
		return this;
	}

	@JsonProperty("systemId")
	public Integer getSystemId() {
		return systemId;
	}

	@JsonProperty("systemId")
	public void setSystemId( Integer systemId ) {
		this.systemId = systemId;
	}

	@JsonProperty("systemName")
	public String getSystemName() {
		return systemName;
	}

	@JsonProperty("systemName")
	public void setSystemName( String systemName ) {
		this.systemName = systemName;
	}

	@JsonProperty("stationId")
	public Integer getStationId() {
		return stationId;
	}

	@JsonProperty("stationId")
	public void setStationId( Integer stationId ) {
		this.stationId = stationId;
	}

	@JsonProperty("stationName")
	public String getStationName() {
		return stationName;
	}

	@JsonProperty("stationName")
	public void setStationName( String stationName ) {
		this.stationName = stationName;
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