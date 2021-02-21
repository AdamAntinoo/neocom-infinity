package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.dimensinfin.eveonline.neocom.domain.Corporation;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"pilotId",
		"name",
		"description",
		"corporationId",
		"corporation",
		"birthday",
		"gender",
		"securityStatus",
		"url4Icon",
		"raceData",
		"ancestryData",
		"bloodlineData",
		"totalSkillpoints",
		"walletBalance",
		"currentShipName",
		"currentShipTypeName",
		"lastKnownLocation"
})
public class PilotDto {

	@JsonIgnore
	private final Map<String, Object> additionalProperties = new HashMap<>();
	@JsonProperty("pilotId")
	private Integer pilotId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("description")
	private String description;
	@JsonProperty("corporationId")
	private Integer corporationId;
	@JsonProperty("corporation")
	private org.dimensinfin.eveonline.neocom.domain.Corporation corporation;
	@JsonProperty("birthday")
	private String birthday;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("securityStatus")
	private Double securityStatus;
	@JsonProperty("url4Icon")
	private String url4Icon;
	@JsonProperty("raceData")
	private RaceData raceData;
	@JsonProperty("ancestryData")
	private AncestryData ancestryData;
	@JsonProperty("bloodlineData")
	private BloodlineData bloodlineData;
	@JsonProperty("totalSkillpoints")
	private Integer totalSkillpoints;
	@JsonProperty("walletBalance")
	private Double walletBalance;
	@JsonProperty("currentShipName")
	private String currentShipName;
	@JsonProperty("currentShipTypeName")
	private String currentShipTypeName;
	@JsonProperty("lastKnownLocation")
	private LastKnownLocation lastKnownLocation;

	// - G E T T E R S   &   S E T T E R S
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonProperty("ancestryData")
	public AncestryData getAncestryData() {
		return this.ancestryData;
	}

	@JsonProperty("ancestryData")
	public void setAncestryData( final AncestryData ancestryData ) {
		this.ancestryData = ancestryData;
	}

	@JsonProperty("birthday")
	public String getBirthday() {
		return this.birthday;
	}

	@JsonProperty("birthday")
	public void setBirthday( final String birthday ) {
		this.birthday = birthday;
	}

	@JsonProperty("bloodlineData")
	public BloodlineData getBloodlineData() {
		return this.bloodlineData;
	}

	@JsonProperty("bloodlineData")
	public void setBloodlineData( final BloodlineData bloodlineData ) {
		this.bloodlineData = bloodlineData;
	}

	@JsonProperty("corporation")
	public Corporation getCorporation() {
		return this.corporation;
	}

	@JsonProperty("corporation")
	public void setCorporation( final Corporation corporation ) {
		this.corporation = corporation;
	}

	@JsonProperty("corporationId")
	public Integer getCorporationId() {
		return this.corporationId;
	}

	@JsonProperty("corporationId")
	public PilotDto setCorporationId( final Integer corporationId ) {
		this.corporationId = corporationId;
		return this;
	}

	@JsonProperty("currentShipName")
	public String getCurrentShipName() {
		return this.currentShipName;
	}

	@JsonProperty("currentShipName")
	public void setCurrentShipName( final String currentShipName ) {
		this.currentShipName = currentShipName;
	}

	@JsonProperty("currentShipTypeName")
	public String getCurrentShipTypeName() {
		return this.currentShipTypeName;
	}

	@JsonProperty("currentShipTypeName")
	public void setCurrentShipTypeName( final String currentShipTypeName ) {
		this.currentShipTypeName = currentShipTypeName;
	}

	@JsonProperty("description")
	public String getDescription() {
		return this.description;
	}

	@JsonProperty("description")
	public void setDescription( final String description ) {
		this.description = description;
	}

	@JsonProperty("gender")
	public String getGender() {
		return this.gender;
	}

	@JsonProperty("gender")
	public void setGender( final String gender ) {
		this.gender = gender;
	}

	@JsonProperty("lastKnownLocation")
	public LastKnownLocation getLastKnownLocation() {
		return this.lastKnownLocation;
	}

	@JsonProperty("lastKnownLocation")
	public void setLastKnownLocation( final LastKnownLocation lastKnownLocation ) {
		this.lastKnownLocation = lastKnownLocation;
	}

	@JsonProperty("name")
	public String getName() {
		return this.name;
	}

	@JsonProperty("name")
	public void setName( final String name ) {
		this.name = name;
	}

	@JsonProperty("pilotId")
	public Integer getPilotId() {
		return this.pilotId;
	}

	@JsonProperty("pilotId")
	public void setPilotId( final Integer pilotId ) {
		this.pilotId = pilotId;
	}

	@JsonProperty("raceData")
	public RaceData getRaceData() {
		return this.raceData;
	}

	@JsonProperty("raceData")
	public void setRaceData( final RaceData raceData ) {
		this.raceData = raceData;
	}

	@JsonProperty("securityStatus")
	public Double getSecurityStatus() {
		return this.securityStatus;
	}

	@JsonProperty("securityStatus")
	public void setSecurityStatus( final Double securityStatus ) {
		this.securityStatus = securityStatus;
	}

	@JsonProperty("totalSkillpoints")
	public Integer getTotalSkillpoints() {
		return this.totalSkillpoints;
	}

	@JsonProperty("totalSkillpoints")
	public void setTotalSkillpoints( final Integer totalSkillpoints ) {
		this.totalSkillpoints = totalSkillpoints;
	}

	@JsonProperty("url4Icon")
	public String getUrl4Icon() {
		return this.url4Icon;
	}

	@JsonProperty("url4Icon")
	public void setUrl4Icon( final String url4Icon ) {
		this.url4Icon = url4Icon;
	}

	@JsonProperty("walletBalance")
	public Double getWalletBalance() {
		return this.walletBalance;
	}

	@JsonProperty("walletBalance")
	public void setWalletBalance( final Double walletBalance ) {
		this.walletBalance = walletBalance;
	}

	@JsonAnySetter
	public void setAdditionalProperty( final String name, final Object value ) {
		this.additionalProperties.put( name, value );
	}

}
