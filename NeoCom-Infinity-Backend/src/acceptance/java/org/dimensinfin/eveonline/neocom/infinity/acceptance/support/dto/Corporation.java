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
		"rel",
		"href"
})
public class Corporation {

	@JsonProperty("rel")
	private String rel;
	@JsonProperty("href")
	private String href;
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new HashMap<>();

// - G E T T E R S   &   S E T T E R S
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonProperty("href")
	public String getHref() {
		return this.href;
	}

	@JsonProperty("href")
	public void setHref( final String href ) {
		this.href = href;
	}

	@JsonProperty("rel")
	public String getRel() {
		return this.rel;
	}

	@JsonProperty("rel")
	public void setRel( final String rel ) {
		this.rel = rel;
	}

	@JsonAnySetter
	public void setAdditionalProperty( final String name, final Object value ) {
		this.additionalProperties.put( name, value );
	}

}
