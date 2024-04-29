package org.dimensinfin.eveonline.neocom.infinity.backend.authorization.rest.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthorizationTokenRequest;
import org.dimensinfin.eveonline.neocom.infinity.backend.authorization.domain.AuthorizationTokenResponse;
import org.dimensinfin.eveonline.neocom.infinity.backend.support.InstanceGenerator;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.GlobalRestExceptionHandler;

@SpringBootTest
public class AuthorizationControllerV2Test {
	private MockMvc mvc;
	//    @InjectMocks
	private AuthorizationControllerV1 authorizationController;
	//    @InjectMocks
	private AuthorizationServiceV1 authorizationServiceV1;

	@BeforeEach
	void setUp() {
		this.authorizationServiceV1 = Mockito.mock( AuthorizationServiceV1.class );
		this.authorizationController = new AuthorizationControllerV1( this.authorizationServiceV1 );
		final AuthorizationTokenResponse authorizationResponse = new InstanceGenerator().generateAuthorizationTokenResponse();
		Mockito.when( this.authorizationServiceV1.validateAuthorizationToken(
			Mockito.any( AuthorizationTokenRequest.class )
		) ).thenReturn( authorizationResponse );
		this.mvc = MockMvcBuilders.standaloneSetup( this.authorizationController )
			.setControllerAdvice( new GlobalRestExceptionHandler() )
			.build();
	}

	//    @Test
	void whenTheRequestToValidateAuthorizationToken() throws Exception {
		final MockHttpServletResponse response = mvc.perform(
				MockMvcRequestBuilders.get( "/api/v1/neocom/validateAuthorizationToken" )
					.param( "code", "-CODE-" )
					.param( "state", "-STATE-" )
					.param( "dataSource", "-TRANQUILITY-" )
					.accept( MediaType.APPLICATION_JSON )
					.contentType( MediaType.APPLICATION_JSON )
			)
			.andReturn().getResponse();
		Assertions.assertNotNull( response );
	}
}
