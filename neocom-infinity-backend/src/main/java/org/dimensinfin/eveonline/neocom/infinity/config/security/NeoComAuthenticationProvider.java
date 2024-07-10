package org.dimensinfin.eveonline.neocom.infinity.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.infinity.core.exception.ErrorInfo;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComAuthorizationException;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRestError;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendExceptionObsolete;

@Component
public class NeoComAuthenticationProvider implements AuthenticationProvider {
	public static final ObjectMapper jsonMapper = new ObjectMapper();

	public static NeoComRestError errorAUTHENTICATION_NOT_PRESENT() {
		return new NeoComRestError.Builder()
				.withErrorName( "AUTHENTICATION_NOT_PRESENT" )
				.withHttpStatus( HttpStatus.UNAUTHORIZED )
				.withErrorCode( "dimensinfin.neocom.authorization.unauthorized" )
				.withMessage( "The required authentication data is not present on the request." )
				.build();
	}

	public static NeoComRestError errorPILOT_ACCESS_NOT_AUTHORIZED() {
		return new NeoComRestError.Builder()
				.withErrorName( "PILOT_ACCESS_NOT_AUTHORIZED" )
				.withHttpStatus( HttpStatus.FORBIDDEN )
				.withErrorCode( "dimensinfin.neocom.authorization.unauthorized" )
				.withMessage( "The access to the pilot data is not authorized to the requester credential." )
				.build();
	}

	// - G E T T E R S   &   S E T T E R S
	public Integer getAuthenticatedCorporation() throws IOException {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final String payloadBase64 = (String) authentication.getPrincipal();
		final String payloadString = new String( Base64.decodeBase64( payloadBase64.getBytes() ) );
		final JwtPayload payload = jsonMapper.readValue( payloadString, JwtPayload.class );
		if (null == payload) throw new NeoComAuthorizationException( ErrorInfo.CORPORATION_ID_NOT_AUTHORIZED );
		return payload.getCorporationId();
	}

	public Integer getAuthenticatedPilot() {
		return this.accessDecodedPayload( new NeoComRuntimeBackendExceptionObsolete( errorPILOT_ACCESS_NOT_AUTHORIZED() ) ).getPilotId();
	}

	public String getAuthenticatedUniqueId() {
		return this.accessDecodedPayload( new NeoComRuntimeBackendExceptionObsolete( errorPILOT_ACCESS_NOT_AUTHORIZED() ) ).getUniqueId();
	}

	@Override
	public Authentication authenticate( final Authentication authentication ) throws AuthenticationException {
		return new UsernamePasswordAuthenticationToken( authentication.getPrincipal(), null, new ArrayList<>() );
	}

	@Override
	public boolean supports( final Class<?> authentication ) {
		return authentication.equals( UsernamePasswordAuthenticationToken.class );
	}

	public void validatePilotIdentifier( final int pilotId ) {
		if (pilotId != this.getAuthenticatedPilot().intValue())
			throw new NeoComRuntimeBackendExceptionObsolete( errorPILOT_ACCESS_NOT_AUTHORIZED() );
	}

	private JwtPayload accessDecodedPayload( final NeoComRuntimeBackendExceptionObsolete configuredException ) {
		try {
			final Authentication authentication = Objects.requireNonNull( SecurityContextHolder.getContext().getAuthentication() );
			final String payloadBase64 = (String) authentication.getPrincipal();
			final String payloadString = new String( Base64.decodeBase64( payloadBase64.getBytes() ) );
			final JwtPayload payload = jsonMapper.readValue( payloadString, JwtPayload.class );
			if (null == payload) throw configuredException;
			return payload;
		} catch (final IOException ioe) {
			throw configuredException;
		} catch (final NullPointerException npe) {
			throw new NeoComRuntimeBackendExceptionObsolete( errorAUTHENTICATION_NOT_PRESENT() );
		}
	}
}
