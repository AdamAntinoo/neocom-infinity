package org.dimensinfin.eveonline.neocom.infinity.support;

import java.util.Arrays;

import org.apache.commons.lang3.NotImplementedException;

public enum RequestType {
	VALIDATE_AUTHORIZATION_TOKEN_ENDPOINT_NAME( "Validate Authorization Token" ),
	GET_PILOT_DATA_ENDPOINT_NAME( "Get Pilot Data" ),

	GET_SERVER( " Get Server Status" ),
	GET_CORPORATION_ENDPOINT_NAME( "Get Corporation Data" ),
	GET_PILOTS_FITTINGS_ENDPOINT_NAME( "Get Pilot Fittings" ),
	GET_CORPORATION_ASSETS_BY_LOCATION( "Get Corporation Assets By Location" ),
	STORE_CREDENTIAL( "Store Credential" ),
	GET_ITEM_BASIC( "Get Item Basic" ),
	GET_TODAY_MINING_EXTRACTIONS( "Get Today Pilot MiningExtractions" );

	public static RequestType from( final String code ) {
		return Arrays.stream( RequestType.values() )
				.filter( requestType -> requestType.code.equals( code ) )
				.findFirst()
				.orElseThrow( () -> new NotImplementedException( "Request type not implemented." ) );
	}

	private String code;

	RequestType( String code ) {
		this.code = code;
	}
}
