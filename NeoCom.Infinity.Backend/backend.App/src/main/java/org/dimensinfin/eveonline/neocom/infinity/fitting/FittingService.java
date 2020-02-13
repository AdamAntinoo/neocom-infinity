package org.dimensinfin.eveonline.neocom.infinity.fitting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.domain.Fitting;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdFittings200Ok;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetails;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;

@Service
public class FittingService {
	private final ESIDataProviderWrapper esiDataAdapter;
	private final CredentialDetailsService credentialDetailsService;
	private NeoComAuthenticationProvider neoComAuthenticationProvider;

	@Autowired
	public FittingService( final ESIDataProviderWrapper esiDataAdapter,
	                       final CredentialDetailsService credentialDetailsService ) {
		this.esiDataAdapter = esiDataAdapter;
		this.credentialDetailsService = credentialDetailsService;
	}

//	public ResponseEntity<List<Fitting>> getFittingList( final int corporationId ) {
//		final GetCorporationsCorporationIdOk corporationData = this.esiDataAdapter.getCorporationsCorporationId( corporationId );
//		if (null == corporationData)
//			throw new NeoComNotFoundException( ErrorInfo.TARGET_NOT_FOUND, "Pilot", Integer.valueOf( corporationId ).toString() );
//		return new ResponseEntity<Corporation>( this.obtainCorporationData( corporationId ), HttpStatus.OK );
//	}

	public ResponseEntity<List<Fitting>> getPilotFittings( final Integer authorizedPilotId ) {
		final String uniqueId = this.neoComAuthenticationProvider.getAuthenticatedUniqueId();
		final Credential credential = ((CredentialDetails)this.credentialDetailsService.loadUserByUsername( uniqueId )).getCredential();
		final List<GetCharactersCharacterIdFittings200Ok> fittings = this.esiDataAdapter.getCharactersCharacterIdFittings( credential );
		final List<Fitting> fittingList = new ArrayList<>( fittings.size() );
		for ( GetCharactersCharacterIdFittings200Ok esiFitting : fittings ){
			fittingList.add( new Fitting.Builder().withFittingData( esiFitting ).build());
		}
		return new ResponseEntity<>(fittingList, HttpStatus.OK );
	}
}
