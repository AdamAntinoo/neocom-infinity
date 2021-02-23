package org.dimensinfin.eveonline.neocom.infinity.acceptance.support;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.dto.PilotDto;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.dto.ServerStatusDto;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthenticationStateResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingModel;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingBuildConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingConfigurations;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.EsiItemModel;
import org.dimensinfin.eveonline.neocom.loyalty.domain.LoyaltyServiceConfiguration;
import org.dimensinfin.eveonline.neocom.loyalty.persistence.LoyaltyOfferEntity;

public class NewNeoComWorld extends CommonWorld {
	private ResponseEntity<AuthorizationTokenResponse> validateAuthorizationTokenResponseEntity;
	private ResponseEntity<PilotDto> pilotDataResponseEntity;
	private ResponseEntity<List<FittingModel>> pilotFittingsResponseEntity;
	private ResponseEntity<EsiItemModel> itemResponseEntity;
	private Integer fittingIdentifier;
	private ResponseEntity<FittingConfigurations> fittingConfigurationsResponseEntity;
	private ResponseEntity<FittingBuildConfiguration> fittingBuildConfigurationResponseEntity;
	private String datasource;
	private ResponseEntity<ServerStatusDto> serverStatusResponseEntity;
	private ResponseEntity<List<LoyaltyOfferEntity>> loyaltyOffersResponseEntity;
	private Integer corporationId;
	private ResponseEntity<LoyaltyServiceConfiguration> loyaltyUpdateResponseEntity;
	private LoyaltyServiceConfiguration loyaltyServiceConfiguration;
	private ResponseEntity<AuthenticationStateResponse> authenticationStateResponseEntity;
	private Integer pilotId;

	// - G E T T E R S   &   S E T T E R S
	public ResponseEntity<AuthenticationStateResponse> getAuthenticationStateResponseEntity() {
		return this.authenticationStateResponseEntity;
	}

	public NewNeoComWorld setAuthenticationStateResponseEntity( final ResponseEntity<AuthenticationStateResponse> authenticationStateResponseEntity ) {
		this.authenticationStateResponseEntity = authenticationStateResponseEntity;
		return this;
	}

	public Integer getCorporationId() {
		return this.corporationId;
	}

	public NewNeoComWorld setCorporationId( final Integer corporationId ) {
		this.corporationId = corporationId;
		return this;
	}

	public String getDatasource() {
		return this.datasource;
	}

	public NewNeoComWorld setDatasource( final String datasource ) {
		this.datasource = datasource;
		return this;
	}

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

	public ResponseEntity<List<LoyaltyOfferEntity>> getLoyaltyOffersResponseEntity() {
		return this.loyaltyOffersResponseEntity;
	}

	public NewNeoComWorld setLoyaltyOffersResponseEntity( final ResponseEntity<List<LoyaltyOfferEntity>> loyaltyOffersResponseEntity ) {
		this.loyaltyOffersResponseEntity = loyaltyOffersResponseEntity;
		return this;
	}

	public LoyaltyServiceConfiguration getLoyaltyServiceConfiguration() {
		return this.loyaltyServiceConfiguration;
	}

	public NewNeoComWorld setLoyaltyServiceConfiguration( final LoyaltyServiceConfiguration loyaltyServiceConfiguration ) {
		this.loyaltyServiceConfiguration = loyaltyServiceConfiguration;
		return this;
	}

	public ResponseEntity<LoyaltyServiceConfiguration> getLoyaltyUpdateResponseEntity() {
		return this.loyaltyUpdateResponseEntity;
	}

	public NewNeoComWorld setLoyaltyUpdateResponseEntity( final ResponseEntity<LoyaltyServiceConfiguration> loyaltyUpdateResponseEntity ) {
		this.loyaltyUpdateResponseEntity = loyaltyUpdateResponseEntity;
		return this;
	}

	public ResponseEntity<PilotDto> getPilotDataResponseEntity() {
		return this.pilotDataResponseEntity;
	}

	public NewNeoComWorld setPilotDataResponseEntity( final ResponseEntity<PilotDto> pilotDataResponseEntity ) {
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

	public Integer getPilotId() {
		return this.pilotId;
	}

	public NewNeoComWorld setPilotId( final Integer pilotId ) {
		this.pilotId = pilotId;
		return this;
	}

	public ResponseEntity<ServerStatusDto> getServerStatusResponseEntity() {
		return this.serverStatusResponseEntity;
	}

	public NewNeoComWorld setServerStatusResponseEntity( final ResponseEntity<ServerStatusDto> serverStatusResponseEntity ) {
		this.serverStatusResponseEntity = serverStatusResponseEntity;
		return this;
	}

	public ResponseEntity<AuthorizationTokenResponse> getValidateAuthorizationTokenResponseEntity() {
		return this.validateAuthorizationTokenResponseEntity;
	}

	public NewNeoComWorld setValidateAuthorizationTokenResponseEntity( final ResponseEntity<AuthorizationTokenResponse> validateAuthorizationTokenResponseEntity ) {
		this.validateAuthorizationTokenResponseEntity = validateAuthorizationTokenResponseEntity;
		return this;
	}
}
