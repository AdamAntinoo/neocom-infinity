package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"locationType",
		"locationId",
		"stationId",
		"stationName",
		"solarSystemId",
		"solarSystemName",
		"securityClass",
		"securityStatus",
		"constellationId",
		"constellationName",
		"regionId",
		"regionName"
})
public class LastKnownLocation {

	@JsonProperty("locationType")
	private String locationType;
	@JsonProperty("locationId")
	private Integer locationId;
	@JsonProperty("stationId")
	private Integer stationId;
	@JsonProperty("stationName")
	private String stationName;
	@JsonProperty("solarSystemId")
	private Integer solarSystemId;
	@JsonProperty("solarSystemName")
	private String solarSystemName;
	@JsonProperty("securityClass")
	private String securityClass;
	@JsonProperty("securityStatus")
	private Double securityStatus;
	@JsonProperty("constellationId")
	private Integer constellationId;
	@JsonProperty("constellationName")
	private String constellationName;
	@JsonProperty("regionId")
	private Integer regionId;
	@JsonProperty("regionName")
	private String regionName;
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new HashMap<>();

// - G E T T E R S   &   S E T T E R S
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonProperty("constellationId")
	public Integer getConstellationId() {
		return this.constellationId;
	}

	@JsonProperty("constellationId")
	public void setConstellationId( final Integer constellationId ) {
		this.constellationId = constellationId;
	}

	@JsonProperty("constellationName")
	public String getConstellationName() {
		return this.constellationName;
	}

	@JsonProperty("constellationName")
	public void setConstellationName( final String constellationName ) {
		this.constellationName = constellationName;
	}

	@JsonProperty("locationId")
	public Integer getLocationId() {
		return this.locationId;
	}

	@JsonProperty("locationId")
	public void setLocationId( final Integer locationId ) {
		this.locationId = locationId;
	}

	@JsonProperty("locationType")
	public String getLocationType() {
		return this.locationType;
	}

	@JsonProperty("locationType")
	public void setLocationType( final String locationType ) {
		this.locationType = locationType;
	}

	@JsonProperty("regionId")
	public Integer getRegionId() {
		return this.regionId;
	}

	@JsonProperty("regionId")
	public void setRegionId( final Integer regionId ) {
		this.regionId = regionId;
	}

	@JsonProperty("regionName")
	public String getRegionName() {
		return this.regionName;
	}

	@JsonProperty("regionName")
	public void setRegionName( final String regionName ) {
		this.regionName = regionName;
	}

	@JsonProperty("securityClass")
	public String getSecurityClass() {
		return this.securityClass;
	}

	@JsonProperty("securityClass")
	public void setSecurityClass( final String securityClass ) {
		this.securityClass = securityClass;
	}

	@JsonProperty("securityStatus")
	public Double getSecurityStatus() {
		return this.securityStatus;
	}

	@JsonProperty("securityStatus")
	public void setSecurityStatus( final Double securityStatus ) {
		this.securityStatus = securityStatus;
	}

	@JsonProperty("solarSystemId")
	public Integer getSolarSystemId() {
		return this.solarSystemId;
	}

	@JsonProperty("solarSystemId")
	public void setSolarSystemId( final Integer solarSystemId ) {
		this.solarSystemId = solarSystemId;
	}

	@JsonProperty("solarSystemName")
	public String getSolarSystemName() {
		return this.solarSystemName;
	}

	@JsonProperty("solarSystemName")
	public void setSolarSystemName( final String solarSystemName ) {
		this.solarSystemName = solarSystemName;
	}

	@JsonProperty("stationId")
	public Integer getStationId() {
		return this.stationId;
	}

	@JsonProperty("stationId")
	public void setStationId( final Integer stationId ) {
		this.stationId = stationId;
	}

	@JsonProperty("stationName")
	public String getStationName() {
		return this.stationName;
	}

	@JsonProperty("stationName")
	public void setStationName( final String stationName ) {
		this.stationName = stationName;
	}

	@JsonAnySetter
	public void setAdditionalProperty( final String name, final Object value ) {
		this.additionalProperties.put( name, value );
	}

}
