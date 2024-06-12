package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.api.NeoComSupportFeignClient;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.authorization.rest.v1.AuthorizationFeignClientV1;
import org.dimensinfin.eveonline.neocom.infinity.support.ConverterContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;
import org.dimensinfin.eveonline.neocom.infinity.support.RequestType;
import org.dimensinfin.eveonline.neocom.infinity.support.authorization.converter.CucumberTableToCredential;
import org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1.CorporationFeignClientV1;
import org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1.CorporationResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1.LocationAssetContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.fitting.rest.v1.FittingFeignClientV1;
import org.dimensinfin.eveonline.neocom.infinity.support.fitting.rest.v1.FittingResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.miningextraction.rest.support.MiningExtractionsFeignClientSupport;
import org.dimensinfin.eveonline.neocom.infinity.support.miningextraction.rest.v1.MiningExtractionsFeignClientV1;
import org.dimensinfin.eveonline.neocom.infinity.support.neoitem.rest.v1.NeoItemFeignClientV1;
import org.dimensinfin.eveonline.neocom.infinity.support.neoitem.rest.v1.NeoItemTransport;
import org.dimensinfin.eveonline.neocom.infinity.support.pilot.rest.v1.PilotFeignClientV1;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.COOKIE_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.COOKIE_PAYLOAD;

public class NIBCommonSteps extends SupportSteps {
	private static final String CORPORATION_ID = "corporationId";
	private static final String PILOT_ID = "pilotId";
	private static final String ITEM_ID = "itemId";
	private static final String FEATURES_DIRECTORY = "/home/adam/Development/NeoCom/neocom-infinity-backend/backend" +
			".AcceptanceTest/src/test/resources/features";

	private final AuthorizationFeignClientV1 authorizationFeignClient;
	private final CorporationFeignClientV1 corporationFeignClient;
	private final PilotFeignClientV1 pilotFeignClient;
	private final FittingFeignClientV1 fittingFeignClient;
	private final NeoItemFeignClientV1 neoItemFeignClientV1;
	private final MiningExtractionsFeignClientV1 miningExtractionsFeignClientV1;

	private final NeoComSupportFeignClient neoComSupportFeignClient;
	private final MiningExtractionsFeignClientSupport miningExtractionsFeignClientSupport;
	private final CredentialRepository credentialRepository;

	// - C O N S T R U C T O R S
	@Autowired
	public NIBCommonSteps( final ConverterContainer cucumberTableToRequestConverters,
	                       final NeoComWorld neocomWorld,
	                       final AuthorizationFeignClientV1 authorizationFeignClient,
	                       final CorporationFeignClientV1 corporationFeignClient,
	                       final PilotFeignClientV1 pilotFeignClient,
	                       final FittingFeignClientV1 fittingFeignClient,
	                       final NeoItemFeignClientV1 neoItemFeignClient,
	                       final MiningExtractionsFeignClientV1 miningExtractionsFeignClient,
	                       final NeoComSupportFeignClient neoComSupportFeignClient,
	                       final MiningExtractionsFeignClientSupport miningExtractionsFeignClientSupport,
	                       final CredentialRepository credentialRepository ) {
		super( cucumberTableToRequestConverters, neocomWorld );
		this.authorizationFeignClient = authorizationFeignClient;
		this.corporationFeignClient = corporationFeignClient;
		this.pilotFeignClient = pilotFeignClient;
		this.fittingFeignClient = fittingFeignClient;
		this.neoItemFeignClientV1 = neoItemFeignClient;
		this.miningExtractionsFeignClientV1 = miningExtractionsFeignClient;
		this.neoComSupportFeignClient = neoComSupportFeignClient;
		this.miningExtractionsFeignClientSupport = miningExtractionsFeignClientSupport;
		this.credentialRepository = credentialRepository;
	}

	@Given("a ready scheduler")
	public void a_ready_scheduler() throws IOException {
		final int jobCount = this.neoComSupportFeignClient.restartScheduler();
		Assert.assertEquals( 1, jobCount );
	}

	@Given("a request to the {string} endpoint with the next data")
	public void a_request_to_the_endpoint_with_the_next_data( final String endpointName,
	                                                          final List<Map<String, String>> dataTable ) {
		final RequestType requestType = RequestType.from( endpointName );
		switch (requestType) {
			case GET_CORPORATION_ENDPOINT_NAME:
			case GET_CORPORATION_ASSETS_BY_LOCATION:
				final Map<String, String> row = dataTable.get( 0 );
				final Integer corporationId = Integer.valueOf( row.get( CORPORATION_ID ) );
				Assert.assertNotNull( corporationId );
				this.neocomWorld.setCorporationIdentifier( corporationId );
				break;
			case GET_PUBLIC_PILOT_DATA_ENDPOINT_NAME:
			case GET_PILOTS_FITTINGS_ENDPOINT_NAME:
				//			case GET_TODAY_MINING_EXTRACTIONS:
				//				final Map<String, String> rowPilot = dataTable.get( 0 );
				//				final Integer pilotId = Integer.valueOf( rowPilot.get( PILOT_ID ) );
				//				Assert.assertNotNull( pilotId );
				//				this.neocomWorld.setPilotIdentifier( pilotId );
				//				break;
			case GET_ITEM_BASIC:
				final Map<String, String> rowItem = dataTable.get( 0 );
				final Integer itemId = Integer.valueOf( rowItem.get( ITEM_ID ) );
				Assert.assertNotNull( itemId );
				this.neocomWorld.setItemIdentifier( itemId );
				break;

			default:
				throw new NotImplementedException( "Request type not implemented." );
		}
	}

	@Given("an empty this list of Credentials stored at the repository")
	public void an_empty_this_list_of_Credentials_stored_at_the_repository() throws IOException {
		// Send the support message to clean the list of credentials at the repository.
		this.neoComSupportFeignClient.deleteAllCredentials();
		final Integer obtained = this.neoComSupportFeignClient.countCredentials();
		Assert.assertEquals( 0, obtained.intValue() );
	}

	@Given("an empty this list of MiningExtractions stored at the repository")
	public void an_empty_this_list_of_MiningExtractions_stored_at_the_repository() throws IOException {
		//		Assert.assertTrue(
		this.miningExtractionsFeignClientSupport.deleteAllMiningExtractions();
	}

	@Given("authorization token contained in file {string}")
	public void authorization_token_contained_in_file( final String tokenFilePath ) throws IOException {
		// Process special values
		final String jwtToken = Files.readString( Paths.get( FEATURES_DIRECTORY + tokenFilePath ) );
		if ( jwtToken.equalsIgnoreCase( "<null>" ) ) {
			this.neocomWorld.setJwtAuthorizationToken( null );
			return;
		}
		this.neocomWorld.setJwtAuthorizationToken( jwtToken );
	}

	@Given("the next list of cookies")
	public void the_next_list_of_cookies( final List<Map<String, String>> dataTable ) {
		this.neocomWorld.setCookies( new ArrayList<>() );
		for (final Map<String, String> row : dataTable) {
			this.neocomWorld.addCookie( row.get( COOKIE_NAME ), row.get( COOKIE_PAYLOAD ) );
		}
	}

	@When("the {string} request is processed")
	public void the_request_is_processed( final String endpointName ) {
		final RequestType requestType = RequestType.from( endpointName );
		final ResponseEntity responseEntity = this.processRequest( requestType );
		this.neocomWorld.setHttpStatusCode( responseEntity.getStatusCode() );
		this.neocomWorld.setHttpStatusCodeValue( responseEntity.getStatusCodeValue() );
	}

	@Then("the response status code is {int}")
	public void the_response_status_code_is( final Integer httpStatusCodeValue ) {
		Assert.assertNotNull( this.neocomWorld.getHttpStatusCodeValue() );
		Assert.assertEquals( httpStatusCodeValue.intValue(), this.neocomWorld.getHttpStatusCodeValue() );
	}

	@Then("the response status code is {string}")
	public void the_response_status_code_is( final String httpStatusCode ) {
		Assert.assertNotNull( this.neocomWorld.getHttpStatusCode() );
		Assert.assertEquals( httpStatusCode, this.neocomWorld.getHttpStatusCode() );
	}

	@Given("this list of Credentials stored at the repository")
	public void this_list_of_Credentials_stored_at_the_repository( final List<Map<String, String>> dataTable ) throws SQLException {
		for (int i = 0; i < dataTable.size(); i++) {
			final Map<String, String> row = dataTable.get( i );
			final Credential credential = new CucumberTableToCredential().convert( row );
			// - Replace the use of a support endpoint by a direct write of the Credential into the repository since now the application is on the context.
			this.credentialRepository.persist( credential );
			Assertions.assertEquals( this.credentialRepository.findCredentialById( credential.getUniqueCredential() ).getUniqueCredential(),
					credential.getUniqueCredential()
			);
			//			final StoreCredentialRequest storeCredentialRequest = new StoreCredentialRequest.Builder()
			//					.withCredential( credential ).build();
			//			this.neoComSupportFeignClient.storeCredential( storeCredentialRequest );
		}
//		Assert.assertEquals( dataTable.size() );
	}

	private ResponseEntity processRequest( final RequestType requestType ) {
		try {
			switch (requestType) {
				case GET_CORPORATION_ENDPOINT_NAME:
					Assert.assertTrue( this.neocomWorld.getCorporationIdentifier().isPresent() );
					final ResponseEntity<CorporationResponse> corporationDataResponse =
							this.corporationFeignClient.getCorporationData(
									this.neocomWorld.getCorporationIdentifier().get(),
									this.neocomWorld.getJwtAuthorizationToken()
							);
					this.neocomWorld.setCorporationResponseEntity( corporationDataResponse );
					this.neocomWorld.setCorporationResponse( corporationDataResponse.getBody() );
					return corporationDataResponse;
				//				case GET_PUBLIC_PILOT_DATA_ENDPOINT_NAME:
				//					Assert.assertTrue( this.neocomWorld.getPilotIdentifier().isPresent() );
				//					final ResponseEntity<PilotResponse> pilotResponseEntity =
				//							this.pilotFeignClient.getPilotData(
				//									this.neocomWorld.getPilotIdentifier().get(),
				//									this.neocomWorld.getJwtAuthorizationToken()
				//							);
				//					this.neocomWorld.setPilotResponseEntity( pilotResponseEntity );
				//					this.neocomWorld.setPilotResponse( pilotResponseEntity.getBody() );
				//					return pilotResponseEntity;
				case GET_PILOTS_FITTINGS_ENDPOINT_NAME:
					Assert.assertTrue( this.neocomWorld.getPilotIdentifier().isPresent() );
					final ResponseEntity<List<FittingResponse>> fittingResponseEntity =
							this.fittingFeignClient.getPilotFittings(
									this.neocomWorld.getPilotIdentifier().get(),
									this.neocomWorld.getJwtAuthorizationToken()
							);
					this.neocomWorld.setFittingResponseEntity( fittingResponseEntity );
					this.neocomWorld.setFittingResponse( fittingResponseEntity.getBody() );
					return fittingResponseEntity;
				case GET_CORPORATION_ASSETS_BY_LOCATION:
					Assert.assertTrue( this.neocomWorld.getCorporationIdentifier().isPresent() );
					final ResponseEntity<List<LocationAssetContainer>> assetListResponseEntity = this.corporationFeignClient
							.getCorporationAssetsByLocation(
									this.neocomWorld.getCorporationIdentifier().get(),
									this.neocomWorld.getJwtAuthorizationToken()
							);
					this.neocomWorld.setListAssetContainersResponse( assetListResponseEntity );
					return assetListResponseEntity;
				case GET_ITEM_BASIC:
					Assert.assertNotNull( this.neocomWorld.getItemIdentifier() );
					final ResponseEntity<NeoItemTransport> itemBasicResponseEntity = this.neoItemFeignClientV1
							.getItemBasic( this.neocomWorld.getItemIdentifier() );
					this.neocomWorld.setNeoItemBasicResponseEntity( itemBasicResponseEntity );
					return itemBasicResponseEntity;
				case GET_TODAY_MINING_EXTRACTIONS:
					Assert.assertNotNull( this.neocomWorld.getPilotIdentifier() );
					final ResponseEntity<List<MiningExtractionEntity>> miningExtractionsResponseEntity = this.miningExtractionsFeignClientV1
							.getTodayMiningExtractions4Pilot( this.neocomWorld.getPilotIdentifier().get(),
									this.neocomWorld.getJwtAuthorizationToken() );
					this.neocomWorld.setMiningExtractionsResponseEntity( miningExtractionsResponseEntity );
					return miningExtractionsResponseEntity;
				default:
					throw new NotImplementedException( "Request type not implemented." );
			}
		} catch (final IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
