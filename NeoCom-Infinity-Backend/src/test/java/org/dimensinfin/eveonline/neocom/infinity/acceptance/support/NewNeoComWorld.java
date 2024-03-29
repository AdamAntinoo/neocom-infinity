package org.dimensinfin.eveonline.neocom.infinity.acceptance.support;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingModel;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingBuildConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingConfigurations;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.EsiItemModel;
import org.dimensinfin.eveonline.neocom.infinity.pilot.rest.representation.PilotModel;

public class NewNeoComWorld extends CommonWorld {
	private ResponseEntity<ValidateAuthorizationTokenResponse> validateAuthorizationTokenResponseEntity;
	private ResponseEntity<PilotModel> pilotDataResponseEntity;
	private ResponseEntity<List<FittingModel>> pilotFittingsResponseEntity;
	private ResponseEntity<EsiItemModel> itemResponseEntity;
	private Integer fittingIdentifier;
	private ResponseEntity<FittingConfigurations> fittingConfigurationsResponseEntity;
	private ResponseEntity<FittingBuildConfiguration> fittingBuildConfigurationResponseEntity;

	// - G E T T E R S   &   S E T T E R S
	public ResponseEntity<FittingBuildConfiguration> getFittingBuildConfigurationResponseEntity() {
		return this.fittingBuildConfigurationResponseEntity;
	}

	public NewNeoComWorld setFittingBuildConfigurationResponseEntity( final ResponseEntity<FittingBuildConfiguration> fittingBuildConfigurationResponseEntity ) {
		this.fittingBuildConfigurationResponseEntity = fittingBuildConfigurationResponseEntity;
		return this;
	}

	public ResponseEntity<FittingConfigurations> getFittingConfigurationsResponseEntity() {
		return this.fittingConfigurationsResponseEntity;
	}

	public NewNeoComWorld setFittingConfigurationsResponseEntity( final ResponseEntity<FittingConfigurations> fittingConfigurationsResponseEntity ) {
		this.fittingConfigurationsResponseEntity = fittingConfigurationsResponseEntity;
		return this;
	}

	public Integer getFittingIdentifier() {
		return this.fittingIdentifier;
	}

	public NewNeoComWorld setFittingIdentifier( final Integer fittingIdentifier ) {
		this.fittingIdentifier = fittingIdentifier;
		return this;
	}

	public ResponseEntity<EsiItemModel> getItemResponseEntity() {
		return this.itemResponseEntity;
	}

	public NewNeoComWorld setItemResponseEntity( final ResponseEntity<EsiItemModel> itemResponseEntity ) {
		this.itemResponseEntity = itemResponseEntity;
		return this;
	}

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

	public ResponseEntity<ValidateAuthorizationTokenResponse> getValidateAuthorizationTokenResponseEntity() {
		return this.validateAuthorizationTokenResponseEntity;
	}

	public NewNeoComWorld setValidateAuthorizationTokenResponseEntity( final ResponseEntity<ValidateAuthorizationTokenResponse> validateAuthorizationTokenResponseEntity ) {
		this.validateAuthorizationTokenResponseEntity = validateAuthorizationTokenResponseEntity;
		return this;
	}
}
