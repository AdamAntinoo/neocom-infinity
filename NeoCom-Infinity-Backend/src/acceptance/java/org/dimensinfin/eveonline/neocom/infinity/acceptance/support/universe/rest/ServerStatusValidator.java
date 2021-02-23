package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.universe.rest;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.Validator;
import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.dto.ServerStatusDto;

import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.SERVER_NEXT_DOWNTIME;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.SERVER_SERVER_BACKENDVERSION;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.SERVER_SERVER_SDEVERSION;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.SERVER_START_AGO;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.SERVER_START_TIME;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.SERVER_STATUS_PLAYERS;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.SERVER_STATUS_SERVER;

public class ServerStatusValidator implements Validator<ServerStatusDto> {
	@Override
	public boolean validate( final Map<String, String> rowData, final ServerStatusDto record ) {
		if (null != rowData.get( SERVER_STATUS_SERVER ))
			Assertions.assertEquals( rowData.get( SERVER_STATUS_SERVER ), record.getServer() );
		if (null != rowData.get( SERVER_STATUS_PLAYERS ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( SERVER_STATUS_PLAYERS ) ), record.getPlayers() );
		if (null != rowData.get( SERVER_SERVER_BACKENDVERSION ))
			Assertions.assertEquals( rowData.get( SERVER_SERVER_BACKENDVERSION ), record.getBackendVersion() );
		if (null != rowData.get( SERVER_SERVER_SDEVERSION ))
			Assertions.assertEquals( rowData.get( SERVER_SERVER_SDEVERSION ), record.getSDEVersion() );
		if (null != rowData.get( SERVER_START_TIME ))
			Assertions.assertEquals( rowData.get( SERVER_START_TIME ), record.getStart_time() );
		if (null != rowData.get( SERVER_START_AGO ))
			Assertions.assertNotNull( record.getStartAgo() );
		if (null != rowData.get( SERVER_NEXT_DOWNTIME ))
			Assertions.assertNotNull( record.getNextDowntime() );
		return true;
	}
}
