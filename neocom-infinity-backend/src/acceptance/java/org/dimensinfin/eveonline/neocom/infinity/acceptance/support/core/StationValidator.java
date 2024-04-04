package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.core;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.domain.space.Station;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.Validator;

public class StationValidator implements Validator<Station> {
	public static final String STATION_LOCATION_ID = "locationId";
	public static final String STATION_REGION_ID = "regionId";
	public static final String STATION_REGION_NAME = "regionName";
	public static final String STATION_CONSTELLATION_ID = "constellationId";
	public static final String STATION_CONSTELLATION_NAME = "constellationName";
	public static final String STATION_SYSTEM_ID = "systemId";
	public static final String STATION_SYSTEM_NAME = "systemName";
	public static final String STATION_STATION_ID = "stationId";
	public static final String STATION_STATION_NAME = "stationName";

	@Override
	public boolean validate( final Map<String, String> rowData, final Station record ) {
		if (null != rowData.get( STATION_LOCATION_ID ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( STATION_LOCATION_ID ) ),
					record.getLocationId()
			);
		if (null != rowData.get( STATION_REGION_ID ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( STATION_REGION_ID ) ),
					record.getRegionId()
			);
		if (null != rowData.get( STATION_REGION_NAME ))
			Assertions.assertEquals( rowData.get( STATION_REGION_NAME ),
					record.getRegionName()
			);
		if (null != rowData.get( STATION_CONSTELLATION_ID ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( STATION_CONSTELLATION_ID ) ),
					record.getConstellationId()
			);
		if (null != rowData.get( STATION_CONSTELLATION_NAME ))
			Assertions.assertEquals( rowData.get( STATION_CONSTELLATION_NAME ),
					record.getConstellationName()
			);
		if (null != rowData.get( STATION_SYSTEM_ID ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( STATION_SYSTEM_ID ) ),
					record.getSolarSystemId()
			);
		if (null != rowData.get( STATION_SYSTEM_NAME ))
			Assertions.assertEquals( rowData.get( STATION_SYSTEM_NAME ),
					record.getSolarSystemName()
			);
		if (null != rowData.get( STATION_STATION_ID ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( STATION_STATION_ID ) ),
					record.getStationId()
			);
		if (null != rowData.get( STATION_STATION_NAME ))
			Assertions.assertEquals( rowData.get( STATION_STATION_NAME ),
					record.getStationName()
			);
		return true;
	}
}