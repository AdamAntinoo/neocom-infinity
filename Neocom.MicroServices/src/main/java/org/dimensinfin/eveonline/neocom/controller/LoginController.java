//  PROJECT:     NeoCom.Microservices (NEOC.MS)
//  AUTHORS:     Adam Antinoo - adamantinoo.git@gmail.com
//  COPYRIGHT:   (c) 2017-2018 by Dimensinfin Industries, all rights reserved.
//  ENVIRONMENT: Java 1.8 / SpringBoot-1.3.5 / Angular 5.0
//  DESCRIPTION: This is the SpringBoot MicroServices module to run the backend services to complete the web
//               application based on Angular+SB. This is the web version for the NeoCom Android native
//               application. Most of the source code is common to both platforms and this module includes
//               the source for the specific functionality for the backend services.
package org.dimensinfin.eveonline.neocom.controller;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.core.util.Chrono;
import org.dimensinfin.eveonline.neocom.NeoComMicroServiceApplication;
import org.dimensinfin.eveonline.neocom.auth.NeoComOAuth20;
import org.dimensinfin.eveonline.neocom.auth.TokenRequestBody;
import org.dimensinfin.eveonline.neocom.auth.TokenTranslationResponse;
import org.dimensinfin.eveonline.neocom.auth.VerifyCharacterResponse;
import org.dimensinfin.eveonline.neocom.core.NeoComException;
import org.dimensinfin.eveonline.neocom.database.entity.Credential;
import org.dimensinfin.eveonline.neocom.datamngmt.ESINetworkManager;
import org.dimensinfin.eveonline.neocom.datamngmt.GlobalDataManager;
import org.dimensinfin.eveonline.neocom.exception.JsonExceptionInstance;

// - CLASS IMPLEMENTATION ...................................................................................
@RestController
public class LoginController {
	// - S T A T I C - S E C T I O N ..........................................................................
	private static Logger logger = LoggerFactory.getLogger("LoginController");
	private static Hashtable<String, NeoComSession> sessionStore = new Hashtable();

	// - F I E L D - S E C T I O N ............................................................................

	// - C O N S T R U C T O R - S E C T I O N ................................................................
	public LoginController() {
	}

	// - M E T H O D - S E C T I O N ..........................................................................
	// TODO Code commented out until reactivation of functionality required.
//	@CrossOrigin()
//	@RequestMapping(value = "/api/v1/loginlist", method = RequestMethod.GET, produces = "application/json")
//	public Hashtable<String, Login> loginlistEntryPoint (
//			@RequestParam(value = "force", required = false) final String force) {
//		logger.info(">>>>>>>>>>>>>>>>>>>>NEW REQUEST: " + "/api/v1/loginlist");
//		logger.info(">> [LoginController.loginlistEntryPoint]");
//		try {
//			NeoComMSConnector.getSingleton().startChrono();
//			// If we receive a force command we should clear data before executing the request.
//			if ( force != null ) if ( force.equalsIgnoreCase("true") ) DataManagementModelStore.getSingleton().clearLoginList();
//			return AppModelStore.getSingleton().accessLoginList();
//		} catch (RuntimeException rtex) {
//			return new Hashtable<String, Login>();
//		} finally {
//			logger.info("<< [LoginController.loginlistEntryPoint]>[TIMING] Processing Time: - "
//					+ NeoComMSConnector.getSingleton().timeLapse());
//		}
//	}

	// TODO Code commented out until reactivation of functionality required.
//	@CrossOrigin()
//	@RequestMapping(value = "/api/v1/credentials", method = RequestMethod.GET, produces = "application/json")
//	public String credentialsEntryPoint( @RequestParam(value = "force", required = false) final String force ) {
//		logger.info(">>>>>>>>>>>>>>>>>>>>NEW REQUEST: " + "/api/v1/credentials");
//		logger.info(">> [LoginController.credentialsEntryPoint]");
//		final Chrono totalElapsed = new Chrono();
//		try {
//			// If we receive a force command we should clear data before executing the request.
//			if (force != null) if (force.equalsIgnoreCase("true")) DataManagementModelStore.getSingleton().cleanModel();
//			final List<Credential> credentials = DataManagementModelStore.accessCredentialList();
//			// Serialize the credentials as the Angular UI requires.
//			return serializeCredentialList(credentials);
//		} catch (RuntimeException rtex) {
//			return new JsonExceptionInstance(rtex.getMessage()).toJson();
//		} finally {
//			logger.info("<< [LoginController.credentialsEntryPoint]> [TIMING] Processing Time: {}", totalElapsed.printElapsed(Chrono.ChronoOptions.SHOWMILLIS));
//		}
//	}

	@CrossOrigin()
	@RequestMapping(value = "/api/v1/getauthorizationurl", method = RequestMethod.GET, produces = "application/json")
	public String getAuthorizationURLRequestEntryPoint() {
		logger.info(">>>>>>>>>>>>>>>>>>>>NEW REQUEST: /api/v1/getauthorizationurl");
		logger.info(">> [LoginController.getAuthorizationURLRequestEntryPoint]");
		final Chrono totalElapsed = new Chrono();
		try {
			final NeoComOAuth20 service = new NeoComOAuth20(GlobalDataManager.getResourceString("R.esi.authorization.clientid")
					, GlobalDataManager.getResourceString("R.esi.authorization.secretkey")
					, GlobalDataManager.getResourceString("R.esi.authorization.callback")
					, GlobalDataManager.getResourceString("R.esi.authorization.agent")
					, NeoComOAuth20.ESIStore.DEFAULT
					, ESINetworkManager.constructScopes());
			return service.getAuthorizationUrl();
		} catch (RuntimeException rtex) {
			return new JsonExceptionInstance(rtex.getMessage()).toJson();
		} finally {
			logger.info("<< [LoginController.getAuthorizationURLRequestEntryPoint]> [TIMING] Processing Time: {}", totalElapsed.printElapsed(Chrono.ChronoOptions.SHOWMILLIS));
		}
	}

	@CrossOrigin()
	@RequestMapping(value = "/api/v1/exchangeauthorization/{code}/publickey/{publickey}"
			, method = RequestMethod.GET, produces = "application/json")
	public String exchangeAuthorizationEntryPoint( @PathVariable final String code, @PathVariable final String publickey ) {
		logger.info(">>>>>>>>>>>>>>>>>>>>NEW REQUEST: " + "/api/v1/exchangeauthorization/{}/publickey/{}", code, publickey);
		logger.info(">> [LoginController.exchangeAuthorizationEntryPoint]");
		final Chrono totalElapsed = new Chrono();
		try {
			return exchangeAuthorization(code, publickey);
		} catch (RuntimeException rtex) {
			return new JsonExceptionInstance(rtex.getMessage()).toJson();
		} finally {
			logger.info("<< [LoginController.exchangeAuthorizationEntryPoint]> [TIMING] Processing Time: {}"
					, totalElapsed.printElapsed(Chrono.ChronoOptions.SHOWMILLIS));
		}
	}

	private String exchangeAuthorization( final String authCode, final String publickey ) {
		// Create the conversion call by coding.
		logger.info("-- [LoginController.exchangeAuthorization]> Preparing Verification HTTP request.");
		logger.info("-- [LoginController.exchangeAuthorization]> Creating access token request.");
		try {
			// Create a Retrofit request service to encapsulate the call.
			final GetAccessToken serviceGetAccessToken = new Retrofit.Builder()
					.baseUrl(GlobalDataManager.getResourceString("R.esi.authorization.authorizationserver"))
					.addConverterFactory(JacksonConverterFactory.create())
					.build()
					.create(GetAccessToken.class);
			logger.info("-- [LoginController.exchangeAuthorization]> Creating request body.");
			final TokenRequestBody tokenRequestBody = new TokenRequestBody()
					.setCode(authCode);
			logger.info("-- [LoginController.exchangeAuthorization]> Creating request call.");
			final String peckString = GlobalDataManager.getResourceString("R.esi.authorization.clientid")
					+ ":"
					+ GlobalDataManager.getResourceString("R.esi.authorization.secretkey");
			final String peck = Base64.getEncoder().encodeToString(peckString.getBytes());
			final Call<TokenTranslationResponse> request = serviceGetAccessToken.getAccessToken("Basic " + peck
					, GlobalDataManager.getResourceString("R.esi.authorization.contenttype")
					, tokenRequestBody);
			// Getting the request response to be stored if valid.
			final Response<TokenTranslationResponse> response = request.execute();
			if (response.isSuccessful()) {
				logger.info("-- [LoginController.exchangeAuthorization]> Response is 200 OK.");
				final TokenTranslationResponse token = response.body();
				// Create a security verification instance.
				OkHttpClient.Builder verifyClient =
						new OkHttpClient.Builder()
								.certificatePinner(
										new CertificatePinner.Builder()
												.add("login.eveonline.com", "sha256/5UeWOuDyX7IUmcKnsVdx+vLMkxEGAtzfaOUQT/caUBE=")
												.add("login.eveonline.com", "sha256/980Ionqp3wkYtN9SZVgMzuWQzJta1nfxNPwTem1X0uc=")
												.add("login.eveonline.com", "sha256/du6FkDdMcVQ3u8prumAo6t3i3G27uMP2EOhR8R0at/U=")
												.build())
								.addInterceptor(chain -> chain.proceed(
										chain.request()
												.newBuilder()
												.addHeader("User-Agent", "org.dimensinfin")
												.build()));
				// Verify the character authenticated and create the Credential.
				logger.info("-- [LoginController.exchangeAuthorization]> Creating character verification.");
				final VerifyCharacter verificationService = new Retrofit.Builder()
						.baseUrl(GlobalDataManager.getResourceString("R.esi.authorization.authorizationserver"))
						.addConverterFactory(JacksonConverterFactory.create())
						.client(verifyClient.build())
						.build()
						.create(VerifyCharacter.class);
				final String accessToken = token.getAccessToken();
				final Response<VerifyCharacterResponse> verificationResponse = verificationService.getVerification("Bearer " + accessToken).execute();
				if (verificationResponse.isSuccessful()) {
					logger.info("-- [LoginController.exchangeAuthorization]> Character verification OK.");

					// TODO Create a new session store to keep the session data.
					// Create the credential and store it on a new encrypted session.
					final int newAccountIdentifier = Long.valueOf(verificationResponse.body().getCharacterID()).intValue();
					final Credential credential = new Credential(newAccountIdentifier);
					credential.setAccountName(verificationResponse.body().getCharacterName())
							.setAccessToken(token.getAccessToken())
							.setTokenType(token.getTokenType())
							.setExpires(Instant.now().plus(TimeUnit.SECONDS.toMillis(token.getExpires())).getMillis())
							.setRefreshToken(token.getRefreshToken());
//							.store();
					final NeoComSession session = new NeoComSession()
							.setCredential(credential)
							.setPublicKey(publickey);
					sessionStore.put(session.getSessionId(), session);
					// Clean up the list of credential to force a reload on next access.
					// TODO This is a new code flow to store credentials only on the session and this on the database.

//					DataManagementModelStore.getSingleton().cleanModel();
//					// Update the Pilot information.
//					GlobalDataManager.getPilotV2(credential.getAccountId());
					return "{ \"sessionId\": \""+session.identifier+"\" }";
				} else
					return exceptionSerialization(new NeoComException("NE [LoginController.exchangeAuthorization]> the VerifyCharacterResponse response is invalid. "
							+ verificationResponse.message()));
			} else
				return exceptionSerialization(new NeoComException("NE [LoginController.exchangeAuthorization]> the TokenTranslationResponse response is invalid. "
						+ response.message()));
		} catch (IOException ioe) {
			logger.error("ER [LoginController.exchangeAuthorization]> IO Exception on authorization request call. " + ioe
					.getMessage());
			return exceptionSerialization(ioe);
		} catch (NoSuchAlgorithmException nsae) {
			logger.error("ER [LoginController.exchangeAuthorization]> IO Exception on authorization request call. " + nsae
					.getMessage());
			return exceptionSerialization(nsae);
		} catch (Exception ge) {
			logger.error("ER [LoginController.exchangeAuthorization]> IO Exception on authorization request call. " + ge
					.getMessage());
			return exceptionSerialization(ge);
		} finally {
			// All went perfect. signal the end of the process
			logger.info("<< [LoginController.exchangeAuthorization]");
		}
	}

	private String exceptionSerialization( final Exception exc ) {
		String serializedException = null;
		try {
			serializedException = NeoComMicroServiceApplication.jsonMapper.writeValueAsString(exc);
		} catch (JsonProcessingException jpe) {
			jpe.printStackTrace();
			serializedException = new JsonExceptionInstance(jpe.getMessage()).toJson();
		}
		return serializedException;
	}

	private String serializeCredentialList( final List<Credential> credentials ) {
		// Use my own serialization control to return the data to generate exactly what I want.
		String contentsSerialized = "[jsonClass: \"Exception\"," +
				"message: \"Unprocessed data. Possible JsonProcessingException exception.\"]";
		try {
			contentsSerialized = NeoComMicroServiceApplication.jsonMapper.writeValueAsString(credentials);
		} catch (JsonProcessingException jpe) {
			jpe.printStackTrace();
			contentsSerialized = new JsonExceptionInstance(jpe.getMessage()).toJson();
		}
		return contentsSerialized;
	}

	public interface VerifyCharacter {
		@GET("/oauth/verify")
		Call<VerifyCharacterResponse> getVerification( @Header("Authorization") String token );
	}

	public interface GetAccessToken {
		//		@POST("/persistPerson")
		@POST("/oauth/token")
		Call<TokenTranslationResponse> getAccessToken( @Header("Authorization") String token
				, @Header("Content-Type") String contentType
				, @Body TokenRequestBody body );
	}
}

final class NeoComSession {
	private PublicKey pubKey = null;
	private PrivateKey privateKey = null;
	public byte[] identifier = null;
	private Credential credential = null;
	private String publicKey = null;

	public NeoComSession() throws NoSuchAlgorithmException {
		KeyPair keyPair = buildKeyPair();
		pubKey = keyPair.getPublic();
		privateKey = keyPair.getPrivate();
	}

	public KeyPair buildKeyPair() throws NoSuchAlgorithmException {
		final int keySize = 2048;
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(keySize);
		return keyPairGenerator.genKeyPair();
	}

	public NeoComSession setCredential( final Credential credential ) throws Exception {
		this.credential = credential;
		identifier = encrypt(privateKey, credential.getValidationCode());
		return this;
	}

	public NeoComSession setPublicKey( final String publicKey ) {
		this.publicKey = publicKey;
		return this;
	}

	public String getSessionId() {
		return credential.getValidationCode();
	}

	public static byte[] encrypt( PrivateKey privateKey, String message ) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

		return cipher.doFinal(message.getBytes());
	}
}
// - UNUSED CODE ............................................................................................
