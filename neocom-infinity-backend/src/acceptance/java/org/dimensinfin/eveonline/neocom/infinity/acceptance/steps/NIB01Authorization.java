package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.api.NeoComSupportFeignClient;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.authorization.validation.JWTTokenValidator;
import org.dimensinfin.eveonline.neocom.infinity.config.security.JwtPayload;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendExceptionObsolete;
import org.dimensinfin.eveonline.neocom.infinity.support.ConverterContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;
import org.dimensinfin.eveonline.neocom.infinity.support.RequestType;
import org.dimensinfin.eveonline.neocom.infinity.support.authorization.converter.CucumberTableToCredential;
import org.dimensinfin.logging.LogWrapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.SECRET;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.TOKEN_ACCOUNT_NAME_FIELD_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.TOKEN_CORPORATION_ID_FIELD_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.TOKEN_PILOT_ID_FIELD_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.TOKEN_PREFIX;
import static org.dimensinfin.eveonline.neocom.infinity.config.security.SecurityConstants.TOKEN_UNIQUE_IDENTIFIER_FIELD_NAME;

public class NIB01Authorization extends SupportSteps {
	private static final String SUB = "sub";
	private static final String ISS = "iss";
	private static final ObjectMapper jsonMapper = new ObjectMapper();
	private final NeoComSupportFeignClient neoComSupportFeignClient;
	//	private final JWTTokenService jwtTokenService;
	//	private final Injector injector; // The global Guice injector singleton
	//	// Guice modules are initialized before the spring context completes
	//	{
	//		LogWrapper.info( "Creating Injector for Guice dependencies..." );
	//		this.injector = Guice.createInjector( new NeoComInfinityBackendDependenciesModule() );
	//	}

	// - C O N S T R U C T O R S
	@Autowired
	public NIB01Authorization( final ConverterContainer cucumberTableToRequestConverters,
	                           final NeoComWorld neocomWorld,
	                           final NeoComSupportFeignClient neoComSupportFeignClient ) {
		super( cucumberTableToRequestConverters, neocomWorld );
		this.neoComSupportFeignClient = neoComSupportFeignClient;
		//		this.jwtTokenService = jwtTokenService;
	}

	//	@Bean
	//	public NeoComDatabaseService dependency_08_NeoComDatabaseService() {
	//		LogWrapper.enter();
	//		return this.injector.getInstance( SBNeoComDBAdapter.class );
	//	}
	//	@Bean
	//	public CredentialRepository dependency_09_CredentialRepository() {
	//		LogWrapper.enter();
	//		return this.injector.getInstance( CredentialRepository.class );
	//	}
	@Then("the JWT generated token has the next contents")
	public void the_JWT_generated_token_has_the_next_contents( final List<Map<String, String>> dataTable ) throws IOException {
		LogWrapper.info( "Creating Injector for Guice dependencies..." );
		Assert.assertNotNull( this.neocomWorld.getJwtAuthorizationToken() );
		final JwtPayload payload = Objects.requireNonNull( this.extractPayload(
				this.neocomWorld.getJwtAuthorizationToken()
		) );
		Assert.assertTrue( new JWTTokenValidator().validate( dataTable.get( 0 ), payload ) );
	}

	@Then("the Validate Authentication response message is {string}")
	public void the_Validate_Authentication_response_message_is( final String stateMessage ) {
		Assertions.assertNotNull( this.neocomWorld.getAuthenticationStateResponseEntity() );
		Assertions.assertNotNull( this.neocomWorld.getAuthenticationStateResponseEntity().getBody() );
		Assertions.assertEquals( stateMessage, this.neocomWorld.getAuthenticationStateResponseEntity().getBody().getState().name() );
	}

	@Then("the Validate Authorization Token response contains a Credential")
	public void the_Validate_Authorization_Token_response_contains_a_Credential() {
		Assert.assertNotNull( this.neocomWorld.getValidateAuthorizationTokenResponseEntity() );
		Assert.assertNotNull( this.neocomWorld.getValidateAuthorizationTokenResponseEntity().getBody() );
		Assert.assertNotNull( this.neocomWorld.getValidateAuthorizationTokenResponseEntity().getBody().getCredential() );
		this.neocomWorld.setCredential( this.neocomWorld.getValidateAuthorizationTokenResponseEntity().getBody().getCredential() );
	}

	@Then("the current world Credential has the next values:")
	public void the_current_world_Credential_has_the_next_values( final List<Map<String, String>> dataTable ) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		throw new io.cucumber.java.PendingException();
	}

	@Given("the next Credential data")
	public void the_next_Credential_data( final List<Map<String, String>> dataTable ) {
		final Map<String, String> row = dataTable.get( 0 );
		final Credential credential = new CucumberTableToCredential().convert( row );
		Assert.assertNotNull( credential );
		this.neocomWorld.setCredential( credential );
	}

	@Deprecated
	@Then("the {string} response contains a valid Credential")
	public void the_response_contains_a_valid_Credential( final String endpointName ) {
		final RequestType requestType = RequestType.from( endpointName );
		switch (requestType) {
			case VALIDATE_AUTHORIZATION_TOKEN:
				Assert.assertNotNull( this.neocomWorld.getValidateAuthorizationTokenResponse().getCredential() );
				// TODO - Validate the credential contents probably against a list of values.
				break;
		}
	}

	@Given("the state field matches {string}")
	public void the_state_field_matches( final String stateValue ) {
		Assert.assertEquals( this.neocomWorld.getValidateAuthorizationTokenRequest().getState(),
				stateValue );
	}

	@Then("there is a Credential record with id {string} at the repository")
	public void there_is_a_Credential_record_with_id_at_the_repository( final String credentialId ) throws IOException {
		final Credential credential = this.neoComSupportFeignClient.findCredentialById( credentialId );
		Assert.assertNotNull( credential );
		Assert.assertEquals( credentialId, credential.getUniqueCredential() );
	}

	private JwtPayload accessDecodedPayload( final String payloadDataEncoded ) {
		try {
			final String[] splitSJWTFields = payloadDataEncoded.split( "\\." );
			if (this.validateTokenHeader( splitSJWTFields[0] )) { // Validate the header contents
				final String base64EncodedBody = splitSJWTFields[1];
				final String payloadData = new String( org.apache.commons.codec.binary.Base64.decodeBase64( base64EncodedBody.getBytes() ) );
				final JwtPayload payload = new ObjectMapper().readValue( payloadData, JwtPayload.class );
				if (null == payload)
					throw new NeoComRuntimeBackendExceptionObsolete( "JWT token malformed payload." );
				return payload;
			} else throw new NeoComRuntimeBackendExceptionObsolete( "JWT token header is malformed. Invalid token" );
		} catch (final IOException ioe) {
			throw new NeoComRuntimeBackendExceptionObsolete( ioe.getMessage() );
		}
	}

	private String extractClaim( final String fieldName, final String token ) throws IOException {
		final DecodedJWT jwtToken = JWT.require( Algorithm.HMAC512( SECRET.getBytes() ) )
				.build()
				.verify( token.replace( TOKEN_PREFIX, "" ) );
		final String payloadBase64 = jwtToken.getPayload();
		final String payloadString = new String( Base64.decodeBase64( payloadBase64.getBytes() ) );
		final JwtPayload payload = jsonMapper.readValue( payloadString, JwtPayload.class );
		switch (fieldName) {
			case SUB:
				return payload.getSub();
			case TOKEN_CORPORATION_ID_FIELD_NAME:
				return payload.getCorporationId().toString();
			case TOKEN_ACCOUNT_NAME_FIELD_NAME:
				return payload.getAccountName();
			case ISS:
				return payload.getIss();
			case TOKEN_UNIQUE_IDENTIFIER_FIELD_NAME:
				return payload.getUniqueId();
			case TOKEN_PILOT_ID_FIELD_NAME:
				return payload.getPilotId().toString();
		}
		return null;
	}

	private JwtPayload extractPayload( final String payloadDataEncoded ) {
		return this.accessDecodedPayload( payloadDataEncoded );
	}

	private boolean validateTokenHeader( final String headerEncoded ) {
		final String headerData = new String( org.apache.commons.codec.binary.Base64.decodeBase64( headerEncoded.getBytes() ) );
		return (headerData.equals( "{\"typ\":\"JWT\",\"alg\":\"HS512\"}" ));
	}
}
