package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.core.security.JwtPayload;
import org.dimensinfin.eveonline.neocom.infinity.support.ConverterContainer;
import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoComSupportFeignClient;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;
import org.dimensinfin.eveonline.neocom.infinity.support.RequestType;
import org.dimensinfin.eveonline.neocom.infinity.support.authorization.converter.CucumberTableToCredential;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.dimensinfin.eveonline.neocom.infinity.core.security.SecurityConstants.SECRET;
import static org.dimensinfin.eveonline.neocom.infinity.core.security.SecurityConstants.TOKEN_ACCOUNT_NAME_FIELD_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.core.security.SecurityConstants.TOKEN_CORPORATION_ID_FIELD_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.core.security.SecurityConstants.TOKEN_PILOT_ID_FIELD_NAME;
import static org.dimensinfin.eveonline.neocom.infinity.core.security.SecurityConstants.TOKEN_PREFIX;
import static org.dimensinfin.eveonline.neocom.infinity.core.security.SecurityConstants.TOKEN_UNIQUE_IDENTIFIER_FIELD_NAME;

public class NIB01Authorization extends SupportSteps {
	private static final String SUB = "sub";
	private static final String ISS = "iss";
	private static final ObjectMapper jsonMapper = new ObjectMapper();
	private NeoComSupportFeignClient neoComSupportFeignClient;

	@Autowired
	public NIB01Authorization( final ConverterContainer cucumberTableToRequestConverters,
	                           final NeoComWorld neocomWorld,
	                           final NeoComSupportFeignClient neoComSupportFeignClient ) {
		super( cucumberTableToRequestConverters, neocomWorld );
		this.neoComSupportFeignClient = neoComSupportFeignClient;
	}

	@Then("the JWT generated token has the next contents")
	public void the_JWT_generated_token_has_the_next_contents( final List<Map<String, String>> dataTable ) throws IOException {
		final Map<String, String> row = dataTable.get( 0 );
		Assert.assertEquals( row.get( SUB ),
				this.extractClaim( SUB, this.neocomWorld.getJwtAuthorizationToken() ) );
		Assert.assertEquals( row.get( TOKEN_CORPORATION_ID_FIELD_NAME ),
				this.extractClaim( TOKEN_CORPORATION_ID_FIELD_NAME, this.neocomWorld.getJwtAuthorizationToken() ) );
		Assert.assertEquals( row.get( TOKEN_ACCOUNT_NAME_FIELD_NAME ),
				this.extractClaim( TOKEN_ACCOUNT_NAME_FIELD_NAME, this.neocomWorld.getJwtAuthorizationToken() ) );
		Assert.assertEquals( row.get( ISS ),
				this.extractClaim( ISS, this.neocomWorld.getJwtAuthorizationToken() ) );
		Assert.assertEquals( row.get( TOKEN_UNIQUE_IDENTIFIER_FIELD_NAME ),
				this.extractClaim( TOKEN_UNIQUE_IDENTIFIER_FIELD_NAME, this.neocomWorld.getJwtAuthorizationToken() ) );
		Assert.assertEquals( row.get( TOKEN_PILOT_ID_FIELD_NAME ),
				this.extractClaim( TOKEN_PILOT_ID_FIELD_NAME, this.neocomWorld.getJwtAuthorizationToken() ) );
	}

	@Given("the next Credential data")
	public void the_next_Credential_data( final List<Map<String, String>> dataTable ) {
		final Map<String, String> row = dataTable.get( 0 );
		final Credential credential = new CucumberTableToCredential().convert( row );
		Assert.assertNotNull( credential );
		this.neocomWorld.setCredential( credential );
	}

	@Then("the {string} response contains a valid Credential")
	public void the_response_contains_a_valid_Credential( final String endpointName ) {
		final RequestType requestType = RequestType.from( endpointName );
		switch (requestType) {
			case VALIDATE_AUTHORIZATION_TOKEN_ENDPOINT_NAME:
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
}