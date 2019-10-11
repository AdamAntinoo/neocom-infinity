package org.dimensinfin.eveonline.neocom.infinity.pilot;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdOk;

@Component
public class GetCharactersCharacterId2PilotDataResponseConverter implements Converter<GetCharactersCharacterIdOk, PilotDataResponse> {
//	private ESIDataAdapterWrapper esiDataAdapter;

//	@Autowired
//	public GetCharactersCharacterId2PilotDataResponseConverter( final ESIDataAdapterWrapper esiDataAdapter ) {
//		this.esiDataAdapter = esiDataAdapter;
//	}

	@Override
	public PilotDataResponse convert( final GetCharactersCharacterIdOk pilotData ) {
		return new PilotDataResponse.Builder()
				.withCorporationId( pilotData.getCorporationId() )
				.withGender( pilotData.getGender() )
				.withName( pilotData.getName() )
				.build();
	}
}
