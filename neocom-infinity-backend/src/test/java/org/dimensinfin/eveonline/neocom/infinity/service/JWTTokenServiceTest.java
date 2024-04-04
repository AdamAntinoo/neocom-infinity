package org.dimensinfin.eveonline.neocom.infinity.service;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;

public class JWTTokenServiceTest {
	private static final String TEST_CREDENTIAL_UNIQUE_ID = "-UNIQUE-ID-";
	private static final String TEST_CREDENTIAL_ACCOUNT_NAME = "-ACCOUNT-NAME-";
	private static final Integer TEST_CREDENTIAL_ACCOUNT_ID = 123456;

	private CredentialRepository credentialRepository;

	@BeforeEach
	public void beforeEach() {
		this.credentialRepository = Mockito.mock( CredentialRepository.class );
	}

	@Test
	public void constructorContract() {
		final JWTTokenService jwtTokenService = new JWTTokenService( this.credentialRepository );
		Assertions.assertNotNull( jwtTokenService );
	}

	@Test
	public void createJWTToken() throws SQLException {
		// Given
		final Integer corporationId = 123456;
		final Credential credential = Mockito.mock( Credential.class );
		// When
		Mockito.when( this.credentialRepository.findCredentialById( Mockito.anyString() ) ).thenReturn( credential );
		Mockito.when( credential.getUniqueCredential() ).thenReturn( TEST_CREDENTIAL_UNIQUE_ID );
		Mockito.when( credential.getAccountName() ).thenReturn( TEST_CREDENTIAL_ACCOUNT_NAME );
		Mockito.when( credential.getAccountId() ).thenReturn( TEST_CREDENTIAL_ACCOUNT_ID );
		// Test
		final JWTTokenService jwtTokenService = new JWTTokenService( this.credentialRepository );
		final String obtained = jwtTokenService.createJWTToken( TEST_CREDENTIAL_UNIQUE_ID, corporationId );
		final String expected = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6MTIzNDU2LCJhY2NvdW50TmFtZSI6Ii1BQ0NPVU5ULU5BTUUtIiwiaXNzIjoiTmVvQ29tLkluZmluaXR5LkJhY2tlbmQiLCJ1bmlxdWVJZCI6Ii1VTklRVUUtSUQtIiwicGlsb3RJZCI6MTIzNDU2fQ.-zKcUJdF-6Dyzn1zWUxxpi4QtgcVIxazjkhiLDQt9YttiROgELKHVDJlpbBwQIViOYx73RgCG8HbW2cHr-XIsg";
		// Assertions
		Assertions.assertNotNull( obtained );
		Assertions.assertEquals( expected, obtained );
	}

	@Test
	public void validateTokenNotValidHeader() {
		// Test
		final JWTTokenService jwtTokenService = new JWTTokenService( this.credentialRepository );
		final boolean obtained = jwtTokenService.validateToken( "-INVALID-eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9" +
				".eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6MTIzNDU2LCJhY2NvdW50TmFtZSI6Ii1BQ0NPVU5ULU5BTUUtIiwiaXNzIjoiTmVvQ29tLkluZmluaXR5LkJhY2tlbmQiLCJ1bmlxdWVJZCI6Ii1VTklRVUUtSUQtIiwicGlsb3RJZCI6MTIzNDU2fQ.-zKcUJdF-6Dyzn1zWUxxpi4QtgcVIxazjkhiLDQt9YttiROgELKHVDJlpbBwQIViOYx73RgCG8HbW2cHr-XIsg" );
		// Assertions
		Assertions.assertFalse( obtained );
	}

	@Test
	public void validateTokenNotValidPayload() {
		// Test
		final JWTTokenService jwtTokenService = new JWTTokenService( this.credentialRepository );
		final boolean obtained = jwtTokenService.validateToken( "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9" +
				".eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhl-INVALID-bnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6MTIzNDU2LCJhY2NvdW50TmFtZSI6Ii1BQ0NPVU5ULU5BTUUtIiwiaXNzIjoiTmVvQ29tLkluZmluaXR5LkJhY2tlbmQiLCJ1bmlxdWVJZCI6Ii1VTklRVUUtSUQtIiwicGlsb3RJZCI6MTIzNDU2fQ.-zKcUJdF-6Dyzn1zWUxxpi4QtgcVIxazjkhiLDQt9YttiROgELKHVDJlpbBwQIViOYx73RgCG8HbW2cHr-XIsg" );
		// Assertions
		Assertions.assertFalse( obtained );
	}

	@Test
	public void validateTokenValid() {
		// Test
		final JWTTokenService jwtTokenService = new JWTTokenService( this.credentialRepository );
		final boolean obtained = jwtTokenService.validateToken( "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9" +
				".eyJzdWIiOiJFU0kgT0F1dGgyIEF1dGhlbnRpY2F0aW9uIiwiY29ycG9yYXRpb25JZCI6MTIzNDU2LCJhY2NvdW50TmFtZSI6Ii1BQ0NPVU5ULU5BTUUtIiwiaXNzIjoiTmVvQ29tLkluZmluaXR5LkJhY2tlbmQiLCJ1bmlxdWVJZCI6Ii1VTklRVUUtSUQtIiwicGlsb3RJZCI6MTIzNDU2fQ.-zKcUJdF-6Dyzn1zWUxxpi4QtgcVIxazjkhiLDQt9YttiROgELKHVDJlpbBwQIViOYx73RgCG8HbW2cHr-XIsg" );
		// Assertions
		Assertions.assertTrue( obtained );
	}
}