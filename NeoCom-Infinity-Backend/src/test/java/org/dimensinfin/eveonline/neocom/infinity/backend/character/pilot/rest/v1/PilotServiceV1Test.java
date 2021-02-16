package org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.character.domain.PilotV1;
import org.dimensinfin.eveonline.neocom.character.domain.PublicPilotV1;
import org.dimensinfin.eveonline.neocom.character.service.CharacterService;
import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetails;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;

public class PilotServiceV1Test {

	private NeoComAuthenticationProvider neoComAuthenticationProvider;
	private CredentialDetailsService credentialDetailsService;
	private CharacterService characterService;

	@BeforeEach
	public void beforeEach() {
		this.neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
		this.credentialDetailsService = Mockito.mock( CredentialDetailsService.class );
		this.characterService = Mockito.mock( CharacterService.class );
	}

	@Test
	public void constructorContract() {
		final PilotServiceV1 serviceV1 = new PilotServiceV1( this.neoComAuthenticationProvider, this.credentialDetailsService,
				this.characterService );
		Assertions.assertNotNull( serviceV1 );
	}

	@Test
	public void getAuthenticatedPilotData() {
		// Given
		final Integer TEST_PILOT_ID = 76534568;
		final String TEST_CREDENTIAL_UNIQUE_ID = "-TEST_CREDENTIAL_UNIQUE_ID-";
		final Integer TEST_CREDENTIAL_ACCOUNT_ID = TEST_PILOT_ID;
		final String TEST_CREDENTIAL_NAME = "-TEST_CREDENTIAL_NAME-";
		final CredentialDetails credentialService = Mockito.mock( CredentialDetails.class );
		final Credential credential = Mockito.mock( Credential.class );
		final PilotV1 pilot = Mockito.mock( PilotV1.class );
		// When
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedUniqueId() ).thenReturn( TEST_CREDENTIAL_UNIQUE_ID );
		Mockito.when( this.credentialDetailsService.loadUserByUsername( Mockito.anyString() ) ).thenReturn( credentialService );
		Mockito.when( credentialService.getCredential() ).thenReturn( credential );
		Mockito.when( credential.getAccountId() ).thenReturn( TEST_CREDENTIAL_ACCOUNT_ID );
		Mockito.when( credential.getName() ).thenReturn( TEST_CREDENTIAL_NAME );
		Mockito.when( this.characterService.getPilotV1( Mockito.any( Credential.class ) ) ).thenReturn( pilot );
		// Test
		final PilotServiceV1 serviceV1 = new PilotServiceV1(
				this.neoComAuthenticationProvider,
				this.credentialDetailsService,
				this.characterService );
		final PilotV1 obtained = serviceV1.getAuthenticatedPilotData( TEST_PILOT_ID );
		// Assertions
		Assertions.assertNotNull( obtained );
	}

	@Test
	public void getPilotPublicData() {
		// Given
		final Integer TEST_PILOT_ID = 76534568;
		final PublicPilotV1 publicPilot = Mockito.mock( PublicPilotV1.class );
		// When
		Mockito.when( this.characterService.getPilotPublicData( Mockito.anyInt() ) ).thenReturn( publicPilot );
		// Test
		final PilotServiceV1 serviceV1 = new PilotServiceV1(
				this.neoComAuthenticationProvider,
				this.credentialDetailsService,
				this.characterService );
		final PublicPilotV1 obtained = serviceV1.getPilotPublicData( TEST_PILOT_ID );
		// Assertions
		Assertions.assertNotNull( obtained );
	}
}