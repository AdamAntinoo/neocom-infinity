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
		"allianceId",
		"description",
		"name",
		"raceId"
})
public class RaceData {

	@JsonProperty("allianceId")
	private Integer allianceId;
	@JsonProperty("description")
	private String description;
	@JsonProperty("name")
	private String name;
	@JsonProperty("raceId")
	private Integer raceId;
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new HashMap<>();

// - G E T T E R S   &   S E T T E R S
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonProperty("allianceId")
	public Integer getAllianceId() {
		return this.allianceId;
	}

	@JsonProperty("allianceId")
	public void setAllianceId( final Integer allianceId ) {
		this.allianceId = allianceId;
	}

	@JsonProperty("description")
	public String getDescription() {
		return this.description;
	}

	@JsonProperty("description")
	public void setDescription( final String description ) {
		this.description = description;
	}

	@JsonProperty("name")
	public String getName() {
		return this.name;
	}

	@JsonProperty("name")
	public void setName( final String name ) {
		this.name = name;
	}

	@JsonProperty("raceId")
	public Integer getRaceId() {
		return this.raceId;
	}

	@JsonProperty("raceId")
	public void setRaceId( final Integer raceId ) {
		this.raceId = raceId;
	}

	@JsonAnySetter
	public void setAdditionalProperty( final String name, final Object value ) {
		this.additionalProperties.put( name, value );
	}

}