package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.io.IOException;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest.v1.CharacterFeignClientV1;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.character.rest.v2.CharacterFeignClientV2;
import org.dimensinfin.eveonline.neocom.infinity.authorization.client.v1.ValidateAuthorizationTokenResponse;
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

	// - C O N S T R U C T O R S
	public WhenTheRequestIsProcessed( final @NotNull NeoComWorld neoComWorld,
	                                  final @NotNull AuthorizationFeignClientV1 authorizationFeignClient,
	                                  final @NotNull CharacterFeignClientV1 characterFeignClientV1,
	                                  final @NotNull CharacterFeignClientV2 characterFeignClientV2 ) {
		super( neoComWorld );
		this.authorizationFeignClient = authorizationFeignClient;
		this.characterFeignClientV1 = characterFeignClientV1;
		this.characterFeignClientV2 = characterFeignClientV2;
	}

	@When("the Validate Authorization Token request is processed")
	public void the_Accounting_Week_Income_request_is_processed() throws IOException {
		this.processRequestByType( RequestType.VALIDATE_AUTHORIZATION_TOKEN_ENDPOINT_NAME );
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
