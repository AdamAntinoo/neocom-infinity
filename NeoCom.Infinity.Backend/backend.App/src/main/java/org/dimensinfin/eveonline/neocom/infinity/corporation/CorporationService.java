package org.dimensinfin.eveonline.neocom.infinity.corporation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.domain.Corporation;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCorporationsCorporationIdOk;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.ErrorInfo;
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.NeoComNotFoundException;
import org.dimensinfin.eveonline.neocom.infinity.pilot.PilotService;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

@Service
public class CorporationService {
	private ESIDataProvider esiDataProvider;
	private final PilotService pilotService;

	@Autowired
	public CorporationService( final ESIDataProviderWrapper esiDataProviderWrapper,
	                           final PilotService pilotService ) {
		this.esiDataProvider = esiDataProviderWrapper.getSingleton();
		this.pilotService = pilotService;
	}

	public ResponseEntity<Corporation> getCorporationData( final int corporationId ) {
		final GetCorporationsCorporationIdOk corporationData = this.esiDataProvider.getCorporationsCorporationId( corporationId );
		if (null == corporationData)
			throw new NeoComNotFoundException( ErrorInfo.TARGET_NOT_FOUND, "Pilot", Integer.toString( corporationId ) );
		return new ResponseEntity<>( this.obtainCorporationData( corporationId ), HttpStatus.OK );
	}

	public Corporation obtainCorporationData( final int corporationId ) {
		final GetCorporationsCorporationIdOk corporationData = this.esiDataProvider.getCorporationsCorporationId( corporationId );
		// Access the rest of the corporation's esi data from the service.
		final Corporation.Builder corporationBuilder = new Corporation.Builder()
				.withCorporationId( corporationId )
				.withCorporationPublicData( corporationData )
				.withCeoPilotData( this.pilotService.obtainPilotData( corporationData.getCeoId() ) );
		if ( null != corporationData.getAllianceId())
			return corporationBuilder.optionslAlliance(
					this.esiDataProvider.getAlliancesAllianceId( corporationData.getAllianceId() ) )
					.build();
		else return corporationBuilder.build();
	}
}
