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
		"bloodlineId",
		"charisma",
		"corporationId",
		"description",
		"intelligence",
		"memory",
		"name",
		"perception",
		"raceId",
		"shipTypeId",
		"willpower"
})
public class BloodlineData {

	@JsonProperty("bloodlineId")
	private Integer bloodlineId;
	@JsonProperty("charisma")
	private Integer charisma;
	@JsonProperty("corporationId")
	private Integer corporationId;
	@JsonProperty("description")
	private String description;
	@JsonProperty("intelligence")
	private Integer intelligence;
	@JsonProperty("memory")
	private Integer memory;
	@JsonProperty("name")
	private String name;
	@JsonProperty("perception")
	private Integer perception;
	@JsonProperty("raceId")
	private Integer raceId;
	@JsonProperty("shipTypeId")
	private Integer shipTypeId;
	@JsonProperty("willpower")
	private Integer willpower;
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new HashMap<>();

// - G E T T E R S   &   S E T T E R S
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonProperty("bloodlineId")
	public Integer getBloodlineId() {
		return this.bloodlineId;
	}

	@JsonProperty("bloodlineId")
	public void setBloodlineId( final Integer bloodlineId ) {
		this.bloodlineId = bloodlineId;
	}

	@JsonProperty("charisma")
	public Integer getCharisma() {
		return this.charisma;
	}

	@JsonProperty("charisma")
	public void setCharisma( final Integer charisma ) {
		this.charisma = charisma;
	}

	@JsonProperty("corporationId")
	public Integer getCorporationId() {
		return this.corporationId;
	}

	@JsonProperty("corporationId")
	public void setCorporationId( final Integer corporationId ) {
		this.corporationId = corporationId;
	}

	@JsonProperty("description")
	public String getDescription() {
		return this.description;
	}

	@JsonProperty("description")
	public void setDescription( final String description ) {
		this.description = description;
	}

	@JsonProperty("intelligence")
	public Integer getIntelligence() {
		return this.intelligence;
	}

	@JsonProperty("intelligence")
	public void setIntelligence( final Integer intelligence ) {
		this.intelligence = intelligence;
	}

	@JsonProperty("memory")
	public Integer getMemory() {
		return this.memory;
	}

	@JsonProperty("memory")
	public void setMemory( final Integer memory ) {
		this.memory = memory;
	}

	@JsonProperty("name")
	public String getName() {
		return this.name;
	}

	@JsonProperty("name")
	public void setName( final String name ) {
		this.name = name;
	}

	@JsonProperty("perception")
	public Integer getPerception() {
		return this.perception;
	}

	@JsonProperty("perception")
	public void setPerception( final Integer perception ) {
		this.perception = perception;
	}

	@JsonProperty("raceId")
	public Integer getRaceId() {
		return this.raceId;
	}

	@JsonProperty("raceId")
	public void setRaceId( final Integer raceId ) {
		this.raceId = raceId;
	}

	@JsonProperty("shipTypeId")
	public Integer getShipTypeId() {
		return this.shipTypeId;
	}

	@JsonProperty("shipTypeId")
	public void setShipTypeId( final Integer shipTypeId ) {
		this.shipTypeId = shipTypeId;
	}

	@JsonProperty("willpower")
	public Integer getWillpower() {
		return this.willpower;
	}

	@JsonProperty("willpower")
	public void setWillpower( final Integer willpower ) {
		this.willpower = willpower;
	}

	@JsonAnySetter
	public void setAdditionalProperty( final String name, final Object value ) {
		this.additionalProperties.put( name, value );
	}

}
