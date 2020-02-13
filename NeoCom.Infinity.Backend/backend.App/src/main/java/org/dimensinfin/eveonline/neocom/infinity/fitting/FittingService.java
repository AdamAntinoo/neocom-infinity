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
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.ErrorInfo;
import org.dimensinfin.eveonline.neocom.infinity.core.exceptions.NeoComNotFoundException;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetails;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;

@Service
public class FittingService {
	private final ESIDataProviderWrapper esiDataAdapter;
	private final CredentialDetailsService credentialDetailsService;
	private final NeoComAuthenticationProvider neoComAuthenticationProvider;

	@Autowired
	public FittingService( final ESIDataProviderWrapper esiDataAdapter,
	                       final CredentialDetailsService credentialDetailsService,
	                       final NeoComAuthenticationProvider neoComAuthenticationProvider ) {
		this.esiDataAdapter = esiDataAdapter;
		this.credentialDetailsService = credentialDetailsService;
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
	}

	public ResponseEntity<List<Fitting>> getPilotFittings( final Integer authorizedPilotId ) {
		final String uniqueId = this.neoComAuthenticationProvider.getAuthenticatedUniqueId();
		final Credential credential = ((CredentialDetails) this.credentialDetailsService.loadUserByUsername( uniqueId )).getCredential();
		final List<GetCharactersCharacterIdFittings200Ok> fittings = this.esiDataAdapter.getCharactersCharacterIdFittings( credential );
		if ( null== fittings)throw new NeoComNotFoundException( ErrorInfo.ESI_DATA_NOT_FOUND, "Fittings",
				Integer.toString( credential.getAccountId() ) );
		final List<Fitting> fittingList = new ArrayList<>(  );
		for (GetCharactersCharacterIdFittings200Ok esiFitting : fittings) {
			fittingList.add( new Fitting.Builder().withFittingData( esiFitting ).build() );
		}
		return new ResponseEntity<>( fittingList, HttpStatus.OK );
	}
}
