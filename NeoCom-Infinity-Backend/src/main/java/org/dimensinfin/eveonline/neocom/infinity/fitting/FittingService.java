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
import org.dimensinfin.eveonline.neocom.infinity.core.exception.ErrorInfo;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComNotFoundException;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetails;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

@Service
public class FittingService {
	private final ESIDataProvider esiDataProvider;
	private final CredentialDetailsService credentialDetailsService;
	private final NeoComAuthenticationProvider neoComAuthenticationProvider;

	@Autowired
	public FittingService( final ESIDataProviderWrapper esiDataProviderWrapper,
	                       final CredentialDetailsService credentialDetailsService,
	                       final NeoComAuthenticationProvider neoComAuthenticationProvider ) {
		this.esiDataProvider = esiDataProviderWrapper.getSingleton();
		this.credentialDetailsService = credentialDetailsService;
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
	}

	public ResponseEntity<List<Fitting>> getPilotFittings() {
		final String uniqueId = this.neoComAuthenticationProvider.getAuthenticatedUniqueId();
		final Credential credential = ((CredentialDetails) this.credentialDetailsService.loadUserByUsername( uniqueId )).getCredential();
		final List<GetCharactersCharacterIdFittings200Ok> fittings = this.esiDataProvider.getCharactersCharacterIdFittings( credential );
		if (null == fittings) throw new NeoComNotFoundException( ErrorInfo.ESI_DATA_NOT_FOUND, "Fittings",
				Integer.toString( credential.getAccountId() ) );
		final List<Fitting> fittingList = new ArrayList<>();
		for (GetCharactersCharacterIdFittings200Ok esiFitting : fittings) {
			fittingList.add( new Fitting.Builder().withFittingData( esiFitting ).build() );
		}
		return new ResponseEntity<>( fittingList, HttpStatus.OK );
	}
}
