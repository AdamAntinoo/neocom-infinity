package org.dimensinfin.eveonline.neocom.infinity.support.miningextraction.converter;

import java.util.Map;

import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.infinity.support.CucumberTableToRequestConverter;
import org.dimensinfin.eveonline.neocom.infinity.support.RequestType;

@Component
public class CucumberTableToMiningExtractionEntityConverter extends CucumberTableToRequestConverter<MiningExtractionEntity> {
	private static final String ID = "id";
	private static final String OWNER_ID = "ownerId";
	private static final String TYPE_ID = "typeId";
	private static final String EXTRACTIONS_DATE = "extractionDate";
	private static final String EXTRACTIONS_HOUR = "extractionHour";
	private static final String QUANTITY = "quantity";
	private static final String SYSTEMID = "systemId";

// - G E T T E R S   &   S E T T E R S
	@Override
	public RequestType getType() {
		return RequestType.VALIDATE_AUTHORIZATION_TOKEN;
	}

	@Override
	public MiningExtractionEntity convert( final Map<String, String> cucumberRow ) {
		return new MiningExtractionEntity.Builder()
				.withId( cucumberRow.get( ID ) )
				.withTypeId( Integer.parseInt( cucumberRow.get( TYPE_ID ) ) )
				.withOwnerId( Integer.parseInt( cucumberRow.get( OWNER_ID ) ) )
				//				.withDelta( 0L )
				.withSolarSystemId( Integer.parseInt( cucumberRow.get( SYSTEMID ) ) )
				.withQuantity( Long.parseLong( cucumberRow.get( QUANTITY ) ) )
				.withExtractionDateName( cucumberRow.get( EXTRACTIONS_DATE ) )
				.withExtractionHour( Integer.parseInt( cucumberRow.get( EXTRACTIONS_HOUR ) ) )
				.build();
	}
}
