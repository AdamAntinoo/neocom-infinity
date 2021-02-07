package org.dimensinfin.eveonline.neocom.infinity.acceptance.steps;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotNull;

import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.ValidateAuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.support.NeoComWorld;

import io.cucumber.java.en.Given;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.VALIDATEAUTHORIZATIONREQUEST_CODE;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.VALIDATEAUTHORIZATIONREQUEST_DATASOURCE;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.VALIDATEAUTHORIZATIONREQUEST_STATE;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class GivenTheNextValidateAuthorizationTokenRequest extends StepSupport {
	// - C O N S T R U C T O R S
	public GivenTheNextValidateAuthorizationTokenRequest( final @NotNull NeoComWorld neoComWorld ) {
		super( neoComWorld );
	}

	@Given("the next Validate Authorization Token Request")
	public void the_next_validate_authorization_token_request( final List<Map<String, String>> dataTable ) {
		this.neocomWorld.setValidateAuthorizationTokenRequest( new ValidateAuthorizationTokenRequest.Builder()
				.withCode( dataTable.get( 0 ).get( VALIDATEAUTHORIZATIONREQUEST_CODE ) )
				.withState( dataTable.get( 0 ).get( VALIDATEAUTHORIZATIONREQUEST_STATE ) )
				.withDataSource( dataTable.get( 0 ).get( VALIDATEAUTHORIZATIONREQUEST_DATASOURCE ) )
				.build()
		);
	}

}