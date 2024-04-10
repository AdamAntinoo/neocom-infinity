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
		"server",
		"players",
		"backendVersion",
		"SDEVersion",
		"start_time",
		"startAgo",
		"nextDowntime"
})
public class ServerStatusDto {

	@JsonIgnore
	private final Map<String, Object> additionalProperties = new HashMap<>();
	@JsonProperty("server")
	private String server;
	@JsonProperty("players")
	private Integer players;
	@JsonProperty("backendVersion")
	private String backendVersion;
	@JsonProperty("SDEVersion")
	private String SDEVersion;
	@JsonProperty("start_time")
	private String start_time;
	@JsonProperty("startAgo")
	private String startAgo;
	@JsonProperty("nextDowntime")
	private String nextDowntime;

	// - G E T T E R S   &   S E T T E R S
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonProperty("backendVersion")
	public String getBackendVersion() {
		return this.backendVersion;
	}

	@JsonProperty("backendVersion")
	public void setBackendVersion( final String backendVersion ) {
		this.backendVersion = backendVersion;
	}

	@JsonProperty("nextDowntime")
	public String getNextDowntime() {
		return this.nextDowntime;
	}

	@JsonProperty("nextDowntime")
	public void setNextDowntime( final String nextDowntime ) {
		this.nextDowntime = nextDowntime;
	}

	@JsonProperty("players")
	public Integer getPlayers() {
		return this.players;
	}

	@JsonProperty("players")
	public void setPlayers( final Integer players ) {
		this.players = players;
	}

	@JsonProperty("SDEVersion")
	public String getSDEVersion() {
		return this.SDEVersion;
	}

	@JsonProperty("SDEVersion")
	public void setSDEVersion( final String sDEVersion ) {
		this.SDEVersion = sDEVersion;
	}

	@JsonProperty("server")
	public String getServer() {
		return this.server;
	}

	@JsonProperty("server")
	public void setServer( final String server ) {
		this.server = server;
	}

	@JsonProperty("startAgo")
	public String getStartAgo() {
		return this.startAgo;
	}

	@JsonProperty("startAgo")
	public void setStartAgo( final String startAgo ) {
		this.startAgo = startAgo;
	}

	@JsonProperty("start_time")
	public String getStart_time() {
		return this.start_time;
	}

	@JsonProperty("start_time")
	public void setStart_time( final String start_time ) {
		this.start_time = start_time;
	}

	@JsonAnySetter
	public void setAdditionalProperty( final String name, final Object value ) {
		this.additionalProperties.put( name, value );
	}
}