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
		"description",
		"iconId",
		"id",
		"name",
		"shortDescription"
})
public class AncestryData {
	@JsonProperty("bloodlineId")
	private Integer bloodlineId;
	@JsonProperty("description")
	private String description;
	@JsonProperty("iconId")
	private Integer iconId;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("shortDescription")
	private String shortDescription;
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

	@JsonProperty("description")
	public String getDescription() {
		return this.description;
	}

	@JsonProperty("description")
	public void setDescription( final String description ) {
		this.description = description;
	}

	@JsonProperty("iconId")
	public Integer getIconId() {
		return this.iconId;
	}

	@JsonProperty("iconId")
	public void setIconId( final Integer iconId ) {
		this.iconId = iconId;
	}

	@JsonProperty("id")
	public Integer getId() {
		return this.id;
	}

	@JsonProperty("id")
	public void setId( final Integer id ) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return this.name;
	}

	@JsonProperty("name")
	public void setName( final String name ) {
		this.name = name;
	}

	@JsonProperty("shortDescription")
	public String getShortDescription() {
		return this.shortDescription;
	}

	@JsonProperty("shortDescription")
	public void setShortDescription( final String shortDescription ) {
		this.shortDescription = shortDescription;
	}

	@JsonAnySetter
	public void setAdditionalProperty( final String name, final Object value ) {
		this.additionalProperties.put( name, value );
	}
}
