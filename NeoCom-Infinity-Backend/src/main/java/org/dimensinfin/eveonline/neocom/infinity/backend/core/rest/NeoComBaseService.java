package org.dimensinfin.eveonline.neocom.infinity.backend.core.rest;

import java.text.MessageFormat;
import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;

import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComError;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

public abstract class NeoComBaseService {

	public static NeoComError errorTARGETNOTFOUND( final String entityName, final Integer identifier ) {
		return new NeoComError.Builder()
				.withErrorName( "TARGET_NOT_FOUND" )
				.withHttpStatus( HttpStatus.NOT_FOUND )
				.withErrorCode( "dimensinfin.neocom.entity.not.found" )
				.withMessage( MessageFormat.format(
						"The entity of class {0} with identifier {1} is not found.",
						entityName, identifier ) )
				.build();
	}

	protected final ESIDataProvider esiDataProvider;

	// - C O N S T R U C T O R S
	public NeoComBaseService( final @NotNull ESIDataProviderWrapper esiDataProviderWrapper ) {
		this.esiDataProvider = Objects.requireNonNull( esiDataProviderWrapper.getSingleton() );
	}
}
