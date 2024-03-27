package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.io.IOException;
import java.util.List;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest.v1.CharacterFeignClientV1;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest.v2.CharacterFeignClientV2;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.rest.v1.IndustryFeignClientV1;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.universe.rest.v2.UniverseFeignClientV2;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain.FittingModel;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingBuildConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingConfigurations;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.EsiItemModel;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.pilot.rest.representation.PilotModel;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;
import org.dimensinfin.eveonline.neocom.infinity.support.RequestType;
import org.dimensinfin.eveonline.neocom.infinity.support.authorization.rest.v1.AuthorizationFeignClientV1;
import org.dimensinfin.logging.LogWrapper;

import io.cucumber.java.en.When;

public class WhenTheRequestIsProcessed extends StepSupport {
	private final AuthorizationFeignClientV1 authorizationFeignClient;
	private final CharacterFeignClientV1 characterFeignClientV1;
	private final CharacterFeignClientV2 characterFeignClientV2;
	private final UniverseFeignClientV2 universeFeignClientV2;
	private final IndustryFeignClientV1 industryFeignClientV1;

	// - C O N S T R U C T O R S
	public WhenTheRequestIsProcessed( final @NotNull NeoComWorld neoComWorld,
	                                  final @NotNull AuthorizationFeignClientV1 authorizationFeignClient,
	                                  final @NotNull CharacterFeignClientV1 characterFeignClientV1,
	                                  final @NotNull CharacterFeignClientV2 characterFeignClientV2,
	                                  final @NotNull UniverseFeignClientV2 universeFeignClientV2,
	                                  final @NotNull IndustryFeignClientV1 industryFeignClientV1 ) {
		super( neoComWorld );
		this.authorizationFeignClient = authorizationFeignClient;
		this.characterFeignClientV1 = characterFeignClientV1;
		this.characterFeignClientV2 = characterFeignClientV2;
		this.universeFeignClientV2 = universeFeignClientV2;
		this.industryFeignClientV1 = industryFeignClientV1;
	}

	@When("the Validate Authorization Token request is processed")
	public void the_Accounting_Week_Income_request_is_processed() throws IOException {
		this.processRequestByType( RequestType.VALIDATE_AUTHORIZATION_TOKEN_ENDPOINT_NAME );
	}

	@When("the Get EsiItem with item id {int} request is processed")
	public void the_Get_EsiItem_with_item_id_request_is_processed( final Integer itemId ) throws IOException {
		this.neocomWorld.setItemIdentifier( itemId );
		this.processRequestByType( RequestType.GET_ESIITEM_ENDPOINT_NAME );
	}

	@When("the Get Fitting Configurations request with fitting identifier {int} is processed")
	public void the_Get_Fitting_Configurations_request_with_fitting_identifier_is_processed( final Integer fittingId ) throws IOException {
		this.neocomWorld.setFittingIdentifier( fittingId );
		this.processRequestByType( RequestType.GET_INDUSTRY_FITTING_CONFIGURATIONS );
	}

	@When("the Get Fitting Saved Configuration request with fitting identifier {int} is processed")
	public void the_Get_Fitting_Saved_Configuration_request_with_fitting_identifier_is_processed( final Integer fittingId ) throws IOException {
		this.neocomWorld.setFittingIdentifier( fittingId );
		this.processRequestByType( RequestType.GET_INDUSTRY_FITTING_SAVED_CONFIGURATION );
	}

	@When("the Get Pilot Fittings request for pilot {string} is processed")
	public void the_Get_Pilot_Fittings_request_for_pilot_is_processed( final String pilotIdentifier ) throws IOException {
		this.neocomWorld.setPilotIdentifier( Integer.parseInt( pilotIdentifier ) );
		this.processRequestByType( RequestType.GET_PILOTS_FITTINGS_V2_ENDPOINT_NAME );
	}

	@When("the Get Pilot Public Data with pilot identifier {string} request is processed")
	public void the_Get_Pilot_Public_Data_with_pilot_identifier_request_is_processed( final String pilotIdentifier ) throws IOException {
		this.neocomWorld.setPilotIdentifier( Integer.parseInt( pilotIdentifier ) );
		this.processRequestByType( RequestType.GET_PILOT_DATA_ENDPOINT_NAME );
	}

	private ResponseEntity processRequest( final RequestType requestType ) throws IOException {
		switch (requestType) {
			case VALIDATE_AUTHORIZATION_TOKEN_ENDPOINT_NAME:
				final ResponseEntity<ValidateAuthorizationTokenResponse> validateAuthorizationTokenResponseEntity = this.authorizationFeignClient
						.validateAuthorizationToken(
								this.neocomWorld.getValidateAuthorizationTokenRequest()
						);
				Assertions.assertNotNull( validateAuthorizationTokenResponseEntity );
				this.neocomWorld.setValidateAuthorizationTokenResponseEntity( validateAuthorizationTokenResponseEntity );
				this.neocomWorld.setJwtAuthorizationToken( validateAuthorizationTokenResponseEntity.getBody().getJwtToken() );
				return validateAuthorizationTokenResponseEntity;
			case GET_PILOT_DATA_ENDPOINT_NAME:
				Assertions.assertTrue( this.neocomWorld.getPilotIdentifier().isPresent() );
				final ResponseEntity<PilotModel> pilotDataResponseEntity = this.characterFeignClientV2.getPilotData(
						this.neocomWorld.getPilotIdentifier().get(),
						this.neocomWorld.getJwtAuthorizationToken()
				);
				Assertions.assertNotNull( pilotDataResponseEntity );
				this.neocomWorld.setPilotDataResponseEntity( pilotDataResponseEntity );
				return pilotDataResponseEntity;
			case GET_PILOTS_FITTINGS_V2_ENDPOINT_NAME:
				Assertions.assertTrue( this.neocomWorld.getPilotIdentifier().isPresent() );
				final ResponseEntity<List<FittingModel>> pilotFittingsResponseEntity = this.characterFeignClientV2.getPilotFittings(
						this.neocomWorld.getPilotIdentifier().get(),
						this.neocomWorld.getJwtAuthorizationToken()
				);
				Assertions.assertNotNull( pilotFittingsResponseEntity );
				this.neocomWorld.setPilotFittingsResponseEntity( pilotFittingsResponseEntity );
				return pilotFittingsResponseEntity;
			case GET_ESIITEM_ENDPOINT_NAME:
				Assertions.assertNotNull( this.neocomWorld.getItemIdentifier() );
				final ResponseEntity<EsiItemModel> itemResponseEntity = this.universeFeignClientV2.getItem( this.neocomWorld.getItemIdentifier() );
				Assertions.assertNotNull( itemResponseEntity );
				this.neocomWorld.setItemResponseEntity( itemResponseEntity );
				return itemResponseEntity;
			case GET_INDUSTRY_FITTING_CONFIGURATIONS:
				try {
					Assertions.assertNotNull( this.neocomWorld.getFittingIdentifier() );
					final ResponseEntity<FittingConfigurations> fittingConfigurationsResponseEntity = this.industryFeignClientV1
							.getFittingConfigurations(
									this.neocomWorld.getJwtAuthorizationToken(),
									this.neocomWorld.getFittingIdentifier()
							);
					Assertions.assertNotNull( fittingConfigurationsResponseEntity );
					this.neocomWorld.setFittingConfigurationsResponseEntity( fittingConfigurationsResponseEntity );
					return fittingConfigurationsResponseEntity;
				} catch (final IOException ioe) {
					LogWrapper.error( ioe );
				}
			case GET_INDUSTRY_FITTING_SAVED_CONFIGURATION:
				Assertions.assertNotNull( this.neocomWorld.getFittingIdentifier() );
				final ResponseEntity<FittingBuildConfiguration> fittingBuildConfigurationResponseEntity = this.industryFeignClientV1
						.getFittingBuildConfigurationSavedConfiguration(
								this.neocomWorld.getJwtAuthorizationToken(),
								this.neocomWorld.getFittingIdentifier()
						);
				Assertions.assertNotNull( fittingBuildConfigurationResponseEntity );
				this.neocomWorld.setFittingBuildConfigurationResponseEntity( fittingBuildConfigurationResponseEntity );
				return fittingBuildConfigurationResponseEntity;
			default:
				throw new NotImplementedException( "Request {} not implemented.", requestType.name() );
		}
	}

	private void processRequestByType( final RequestType requestType ) throws IOException {
		try {
			final ResponseEntity<?> response = this.processRequest( requestType );
			this.neocomWorld.setHttpStatus( response.getStatusCode() );
		} catch (final NeoComRuntimeBackendException runtime) {
			this.neocomWorld.setHttpStatus( runtime.getHttpStatus() );
			this.neocomWorld.setApplicationException( runtime );
			LogWrapper.error( runtime );
		}
	}
}
