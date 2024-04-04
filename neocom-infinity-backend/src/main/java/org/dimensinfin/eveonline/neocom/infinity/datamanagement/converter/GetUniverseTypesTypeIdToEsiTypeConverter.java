package org.dimensinfin.eveonline.neocom.infinity.datamanagement.converter;

import org.dimensinfin.core.interfaces.Converter;
import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOk;

@Deprecated
public class GetUniverseTypesTypeIdToEsiTypeConverter implements Converter<GetUniverseTypesTypeIdOk, EsiType> {

	@Override
	public EsiType convert( final GetUniverseTypesTypeIdOk input ) {
		return new EsiType.Builder()
				.build();
	}
}
