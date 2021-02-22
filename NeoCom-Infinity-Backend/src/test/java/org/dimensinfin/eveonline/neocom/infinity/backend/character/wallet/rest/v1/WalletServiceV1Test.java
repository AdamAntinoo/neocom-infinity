package org.dimensinfin.eveonline.neocom.infinity.backend.character.wallet.rest.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetails;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;

import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_WALLET_BALANCE;

public class WalletServiceV1Test {
	private static final Integer TEST_CREDENTIAL_ACCOUNT_ID = 32345864;
	private static final String TEST_CREDENTIAL_NAME = "-CREDENTIAL-NAME-";
	private NeoComAuthenticationProvider neoComAuthenticationProvider;
	private CredentialDetailsService credentialDetailsService;
	private ESIDataService esiDataService;

	@BeforeEach
	public void beforeEach() {
		this.neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
		this.credentialDetailsService = Mockito.mock( CredentialDetailsService.class );
		this.esiDataService = Mockito.mock( ESIDataService.class );
		this.credentialAuthentication();
	}

	@Test
	public void constructorContract() {
		final WalletServiceV1 serviceV1 = new WalletServiceV1(
				this.neoComAuthenticationProvider,
				this.credentialDetailsService,
				this.esiDataService
		);
		Assertions.assertNotNull( serviceV1 );
	}

	public void credentialAuthentication() {
		final String uniqueId = "-UNIQUE-ID-";
		final CredentialDetails credentialDetails = Mockito.mock( CredentialDetails.class );
		final Credential credential = Mockito.mock( Credential.class );
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedUniqueId() ).thenReturn( uniqueId );
		Mockito.when( this.credentialDetailsService.loadUserByUsername( Mockito.anyString() ) )
				.thenReturn( credentialDetails );
		Mockito.when( credentialDetails.getCredential() ).thenReturn( credential );
		Mockito.when( credential.getAccountId() ).thenReturn( TEST_CREDENTIAL_ACCOUNT_ID );
		Mockito.when( credential.getName() ).thenReturn( TEST_CREDENTIAL_NAME );
	}

	@Test
	public void getPilotWalletBalance() {
		// When
		Mockito.when( this.esiDataService.getCharactersCharacterIdWallet( Mockito.any( Credential.class ) ) )
				.thenReturn( TEST_PILOT_WALLET_BALANCE );
		// Test
		final WalletServiceV1 serviceV1 = new WalletServiceV1(
				this.neoComAuthenticationProvider,
				this.credentialDetailsService,
				this.esiDataService
		);
		final Double obtained = serviceV1.getPilotWalletBalance();
		final Double expected = TEST_PILOT_WALLET_BALANCE;
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( expected, obtained );
	}
}