package org.dimensinfin.eveonline.neocom.infinity.acceptance.support;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingModel;
import org.dimensinfin.eveonline.neocom.infinity.pilot.rest.representation.PilotModel;

public class NewNeoComWorld extends CommonWorld {
	private ResponseEntity<ValidateAuthorizationTokenResponse> validateAuthorizationTokenResponseEntity;
	private ResponseEntity<PilotModel> pilotDataResponseEntity;
	private ResponseEntity<List<FittingModel>> pilotFittingsResponseEntity;

// - G E T T E R S   &   S E T T E R S
	public ResponseEntity<PilotModel> getPilotDataResponseEntity() {
		return this.pilotDataResponseEntity;
	}

	public NewNeoComWorld setPilotDataResponseEntity( final ResponseEntity<PilotModel> pilotDataResponseEntity ) {
		this.pilotDataResponseEntity = pilotDataResponseEntity;
		return this;
	}

	public ResponseEntity<List<FittingModel>> getPilotFittingsResponseEntity() {
		return this.pilotFittingsResponseEntity;
	}

	public NewNeoComWorld setPilotFittingsResponseEntity( final ResponseEntity<List<FittingModel>> pilotFittingsResponseEntity ) {
		this.pilotFittingsResponseEntity = pilotFittingsResponseEntity;
		return this;
	}
	//	private String jwtAuthorizationToken;

	public ResponseEntity<ValidateAuthorizationTokenResponse> getValidateAuthorizationTokenResponseEntity() {
		return this.validateAuthorizationTokenResponseEntity;
	}

	public NewNeoComWorld setValidateAuthorizationTokenResponseEntity( final ResponseEntity<ValidateAuthorizationTokenResponse> validateAuthorizationTokenResponseEntity ) {
		this.validateAuthorizationTokenResponseEntity = validateAuthorizationTokenResponseEntity;
		return this;
	}
}
