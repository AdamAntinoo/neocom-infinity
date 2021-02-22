package org.dimensinfin.eveonline.neocom.infinity.backend.character.wallet.rest.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;

import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_ID;
import static org.dimensinfin.eveonline.neocom.infinity.backend.support.TestDataConstants.PilotConstants.TEST_PILOT_WALLET_BALANCE;

public class WalletControllerV1Test {
	private NeoComAuthenticationProvider neoComAuthenticationProvider;
	private WalletServiceV1 walletServiceV1;

	@BeforeEach
	public void beforeEach() {
		this.neoComAuthenticationProvider = Mockito.mock( NeoComAuthenticationProvider.class );
		this.walletServiceV1 = Mockito.mock( WalletServiceV1.class );
	}

	@Test
	public void constructorContract() {
		final WalletControllerV1 controllerV1 = new WalletControllerV1( this.neoComAuthenticationProvider, this.walletServiceV1 );
		Assertions.assertNotNull( controllerV1 );
	}

	@Test
	public void getPilotWalletBalance() {
		// Given
		final Integer pilotId = TEST_PILOT_ID;
		// When
		Mockito.when( this.neoComAuthenticationProvider.getAuthenticatedPilot() ).thenReturn( pilotId );
		Mockito.when( this.walletServiceV1.getPilotWalletBalance() ).thenReturn( TEST_PILOT_WALLET_BALANCE );
		// Test
		final WalletControllerV1 controllerV1 = new WalletControllerV1( this.neoComAuthenticationProvider, this.walletServiceV1 );
		final ResponseEntity<Double> obtained = controllerV1.getPilotWalletBalance( pilotId );
		final Double expected = TEST_PILOT_WALLET_BALANCE;
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertNotNull( obtained.getBody() );
		Assertions.assertEquals( expected, obtained.getBody(), 0.1 );
	}
}