package org.dimensinfin.eveonline.neocom.infinity.datamanagement.converter;

import org.dimensinfin.core.interfaces.Converter;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.EsiItem;

public class GetUniverseTypesTypeIdToEsiItemConverter implements Converter<GetUniverseTypesTypeIdOk, EsiItem> {

	@Override
	public EsiItem convert( final GetUniverseTypesTypeIdOk input ) {
		return new EsiItem.Builder()
				.build();
	}
}
