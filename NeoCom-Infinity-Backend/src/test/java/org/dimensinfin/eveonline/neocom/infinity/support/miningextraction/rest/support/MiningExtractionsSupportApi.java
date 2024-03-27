package org.dimensinfin.eveonline.neocom.infinity.support.miningextraction.rest.support;

import javax.validation.Valid;

import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.infinity.mining.rest.support.MiningExtractionCountResponse;
import org.dimensinfin.eveonline.neocom.infinity.mining.rest.support.StoreMiningExtractionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MiningExtractionsSupportApi {
	@GET("/api/v1/neocom/support/miningextractions/deleteall")
	Call<MiningExtractionCountResponse> deleteAllMiningExtractions( @Header("Content-Type") final String contentType );

	@POST("/api/v1/neocom/support/miningextractions")
	Call<StoreMiningExtractionResponse> storeMiningExtraction( @Header("Content-Type") final String contentType,
	                                                           @Body @Valid final MiningExtractionEntity miningExtractionEntity );
}
