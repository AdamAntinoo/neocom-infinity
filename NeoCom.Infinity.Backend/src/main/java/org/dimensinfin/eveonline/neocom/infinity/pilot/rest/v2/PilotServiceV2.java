package org.dimensinfin.eveonline.neocom.infinity.pilot.rest.v2;

import java.text.MessageFormat;
import java.util.Objects;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ConfigurationServiceWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComError;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.pilot.converter.EsiPilotDataToPilotModelConverter;
import org.dimensinfin.eveonline.neocom.infinity.pilot.rest.representation.PilotModel;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;
import org.dimensinfin.eveonline.neocom.provider.IConfigurationService;

@Service
public class PilotServiceV2 {
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

	private final IConfigurationService configurationService;
	private final ESIDataProvider esiDataProvider;

	// - C O N S T R U C T O R S
	public PilotServiceV2( @NotNull final ConfigurationServiceWrapper configurationServiceWrapper,
	                       @NotNull final ESIDataProviderWrapper esiDataProviderWrapper ) {
		this.configurationService = Objects.requireNonNull( configurationServiceWrapper.getSingleton() );
		this.esiDataProvider = Objects.requireNonNull( esiDataProviderWrapper.getSingleton() );
	}

	public PilotModel getPilotData( final Integer pilotId ) {
		final GetCharactersCharacterIdOk pilotData = this.esiDataProvider.getCharactersCharacterId( pilotId );
		if (null == pilotData)
			throw new NeoComRuntimeBackendException( errorTARGETNOTFOUND( "Pilot", pilotId ) );
		return new EsiPilotDataToPilotModelConverter( this.configurationService, this.esiDataProvider, pilotId ).convert( pilotData );

		// Access the rest of the pilot's esi data from the service.
		//		final GetCharactersCharacterIdOk pilotData = this.esiDataProvider.getCharactersCharacterId( pilotId );
//		final Pilot pilot = new Pilot.Builder()
		//				.withPilotIdentifier( pilotId )
		//				.withCharacterPublicData( pilotData )
		//				.withRaceData( this.esiDataProvider.searchSDERace( pilotData.getRaceId() ) )
		//				.withAncestryData( this.esiDataProvider.searchSDEAncestry( pilotData.getAncestryId() ) )
		//				.withBloodlineData( this.esiDataProvider.searchSDEBloodline( pilotData.getBloodlineId() ) )
		//				.build();
		//		return pilot;


	}
}
