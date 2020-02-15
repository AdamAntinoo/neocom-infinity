package org.dimensinfin.eveonline.neocom.infinity.support;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1.CorporationResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.fitting.rest.v1.FittingResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.pilot.rest.v1.PilotResponse;

public class NeoComWorld {
	private HttpStatus httpStatusCode;
	private int httpStatusCodeValue;

	private ValidateAuthorizationTokenRequest validateAuthorizationTokenRequest;
	private ValidateAuthorizationTokenResponse validateAuthorizationTokenResponse;
	private Optional<Integer> corporationIdentifier = Optional.empty();
	private Optional<Integer> pilotIdentifier = Optional.empty();
	private String jwtAuthorizationToken;
	private ResponseEntity<CorporationResponse> corporationResponseEntity;
	private CorporationResponse corporationResponse;
	private ResponseEntity<PilotResponse> pilotResponseEntity;
	private PilotResponse pilotResponse;
	private ResponseEntity<List<FittingResponse>> fittingResponseEntity;
	private List<FittingResponse> fittingResponse;

	public String getHttpStatusCode() {
		return this.httpStatusCode.name();
	}

	public NeoComWorld setHttpStatusCode( final HttpStatus httpStatusCode ) {
		this.httpStatusCode = httpStatusCode;
		return this;
	}

	public int getHttpStatusCodeValue() {
		return this.httpStatusCodeValue;
	}

	public NeoComWorld setHttpStatusCodeValue( final int httpStatusCodeValue ) {
		this.httpStatusCodeValue = httpStatusCodeValue;
		return this;
	}

	public ValidateAuthorizationTokenRequest getValidateAuthorizationTokenRequest() {
		return this.validateAuthorizationTokenRequest;
	}

	public NeoComWorld setValidateAuthorizationTokenRequest( final ValidateAuthorizationTokenRequest validateAuthorizationTokenRequest ) {
		this.validateAuthorizationTokenRequest = validateAuthorizationTokenRequest;
		return this;
	}

	public ValidateAuthorizationTokenResponse getValidateAuthorizationTokenResponse() {
		return this.validateAuthorizationTokenResponse;
	}

	public NeoComWorld setValidateAuthorizationTokenResponse( final ValidateAuthorizationTokenResponse validateAuthorizationTokenResponse ) {
		this.validateAuthorizationTokenResponse = validateAuthorizationTokenResponse;
		return this;
	}

	public Optional<Integer> getCorporationIdentifier() {
		return corporationIdentifier;
	}

	public NeoComWorld setCorporationIdentifier( final Integer corporationIdentifier ) {
		if (null != corporationIdentifier) this.corporationIdentifier = Optional.of( corporationIdentifier );
		return this;
	}

	public Optional<Integer> getPilotIdentifier() {
		return pilotIdentifier;
	}

	public NeoComWorld setPilotIdentifier( final Integer pilotIdentifier ) {
		if (null != pilotIdentifier) this.pilotIdentifier = Optional.of( pilotIdentifier );
		return this;
	}

	public String getJwtAuthorizationToken() {
		return jwtAuthorizationToken;
	}

	public NeoComWorld setJwtAuthorizationToken( final String jwtAuthorizationToken ) {
		this.jwtAuthorizationToken = jwtAuthorizationToken;
		return this;
	}

	public ResponseEntity<CorporationResponse> getCorporationResponseEntity() {
		return this.corporationResponseEntity;
	}

	public NeoComWorld setCorporationResponseEntity( final ResponseEntity<CorporationResponse> corporationResponseEntity ) {
		this.corporationResponseEntity = corporationResponseEntity;
		this.httpStatusCodeValue = corporationResponseEntity.getStatusCodeValue();
		return this;
	}

	public ResponseEntity<PilotResponse> getPilotResponseEntity() {
		return pilotResponseEntity;
	}

	public NeoComWorld setPilotResponseEntity( final ResponseEntity<PilotResponse> pilotResponseEntity ) {
		this.pilotResponseEntity = pilotResponseEntity;
		return this;
	}

	public PilotResponse getPilotResponse() {
		return pilotResponse;
	}

	public NeoComWorld setPilotResponse( final PilotResponse pilotResponse ) {
		this.pilotResponse = pilotResponse;
		return this;
	}

	public CorporationResponse getCorporationResponse() {
		return corporationResponse;
	}

	public NeoComWorld setCorporationResponse( final CorporationResponse corporationResponse ) {
		this.corporationResponse = corporationResponse;
		return this;
	}

	public ResponseEntity<List<FittingResponse>> getFittingResponseEntity() {
		return fittingResponseEntity;
	}

	public NeoComWorld setFittingResponseEntity( final ResponseEntity<List<FittingResponse>> fittingResponseEntity ) {
		this.fittingResponseEntity = fittingResponseEntity;
		return this;
	}

	public List<FittingResponse> getFittingResponse() {
		return fittingResponse;
	}

	public NeoComWorld setFittingResponse( final List<FittingResponse> fittingResponse ) {
		this.fittingResponse = fittingResponse;
		return this;
	}
}
