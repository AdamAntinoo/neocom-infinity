package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.deserializer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class TradeStation {
	@SerializedName("locationType")
	@Expose
	private String locationType;
	@SerializedName("locationId")
	@Expose
	private Integer locationId;
	@SerializedName("stationId")
	@Expose
	private Integer stationId;
	@SerializedName("stationName")
	@Expose
	private String stationName;
	@SerializedName("solarSystemId")
	@Expose
	private Integer solarSystemId;
	@SerializedName("solarSystemName")
	@Expose
	private String solarSystemName;
	@SerializedName("securityClass")
	@Expose
	private String securityClass;
	@SerializedName("securityStatus")
	@Expose
	private Double securityStatus;
	@SerializedName("constellationId")
	@Expose
	private Integer constellationId;
	@SerializedName("constellationName")
	@Expose
	private String constellationName;
	@SerializedName("regionId")
	@Expose
	private Integer regionId;
	@SerializedName("regionName")
	@Expose
	private String regionName;

// - G E T T E R S   &   S E T T E R S
	public Integer getConstellationId() {
		return this.constellationId;
	}

	public void setConstellationId( final Integer constellationId ) {
		this.constellationId = constellationId;
	}

	public String getConstellationName() {
		return this.constellationName;
	}

	public void setConstellationName( final String constellationName ) {
		this.constellationName = constellationName;
	}

	public Integer getLocationId() {
		return this.locationId;
	}

	public void setLocationId( final Integer locationId ) {
		this.locationId = locationId;
	}

	public String getLocationType() {
		return this.locationType;
	}

	public void setLocationType( final String locationType ) {
		this.locationType = locationType;
	}

	public Integer getRegionId() {
		return this.regionId;
	}

	public void setRegionId( final Integer regionId ) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName( final String regionName ) {
		this.regionName = regionName;
	}

	public String getSecurityClass() {
		return this.securityClass;
	}

	public void setSecurityClass( final String securityClass ) {
		this.securityClass = securityClass;
	}

	public Double getSecurityStatus() {
		return this.securityStatus;
	}

	public void setSecurityStatus( final Double securityStatus ) {
		this.securityStatus = securityStatus;
	}

	public Integer getSolarSystemId() {
		return this.solarSystemId;
	}

	public void setSolarSystemId( final Integer solarSystemId ) {
		this.solarSystemId = solarSystemId;
	}

	public String getSolarSystemName() {
		return this.solarSystemName;
	}

	public void setSolarSystemName( final String solarSystemName ) {
		this.solarSystemName = solarSystemName;
	}

	public Integer getStationId() {
		return this.stationId;
	}

	public void setStationId( final Integer stationId ) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return this.stationName;
	}

	public void setStationName( final String stationName ) {
		this.stationName = stationName;
	}
}
