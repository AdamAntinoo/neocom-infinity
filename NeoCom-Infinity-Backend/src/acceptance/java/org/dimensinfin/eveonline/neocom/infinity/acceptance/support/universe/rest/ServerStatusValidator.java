package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.universe.rest;

import java.util.Map;

import org.junit.jupiter.api.Assertions;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.Validator;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.ServerStatus;

import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.SERVER_SERVER_VERSION;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.SERVER_START_TIME;
import static org.dimensinfin.eveonline.neocom.infinity.acceptance.support.AcceptanceFieldMapConstants.SERVER_STATUS_PLAYERS;

public class ServerStatusValidator implements Validator<ServerStatus> {
	@Override
	public boolean validate( final Map<String, String> rowData, final ServerStatus record ) {
		if (null != rowData.get( SERVER_STATUS_PLAYERS ))
			Assertions.assertEquals( Integer.parseInt( rowData.get( SERVER_STATUS_PLAYERS ) ), record.getStatus().getPlayers() );
		if (null != rowData.get( SERVER_SERVER_VERSION ))
			Assertions.assertEquals( rowData.get( SERVER_SERVER_VERSION ), record.getStatus().getServerVersion() );
		if (null != rowData.get( SERVER_START_TIME ))
			Assertions.assertEquals( rowData.get( SERVER_START_TIME ), record.getStatus().getStartTime().toString() );
		return true;
	}
}
