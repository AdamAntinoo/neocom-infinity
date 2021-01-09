package org.dimensinfin.eveonline.neocom.infinity.support.miningextraction.rest.support;

import java.io.IOException;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import org.dimensinfin.eveonline.neocom.database.entities.MiningExtractionEntity;
import org.dimensinfin.eveonline.neocom.infinity.mining.rest.support.MiningExtractionCountResponse;
import org.dimensinfin.eveonline.neocom.infinity.mining.rest.support.StoreMiningExtractionResponse;
import org.dimensinfin.eveonline.neocom.infinity.support.core.CommonFeignClient;
import org.dimensinfin.eveonline.neocom.infinity.support.rest.NeoComApiv1;
import org.dimensinfin.eveonline.neocom.service.logger.NeoComLogger;

import org.dimensinfin.eveonline.neocom.infinity.acceptance.support.ITargetConfiguration;
import retrofit2.Response;
import retrofit2.Retrofit;

@Component
public class MiningExtractionsFeignClientSupport extends CommonFeignClient {
	public MiningExtractionsFeignClientSupport( final @NotNull ITargetConfiguration acceptanceTargetConfig ) {
		super( acceptanceTargetConfig );
	}

	public Integer deleteAllMiningExtractions() throws IOException {
		final String ENDPOINT_MESSAGE = "Request to delete all mining extractions.";
		final Response<MiningExtractionCountResponse> response = new Retrofit.Builder()
				.baseUrl( NeoComApiv1.NEOCOM_BACKEND_APP_HOST )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( MiningExtractionsSupportApi.class )
				.deleteAllMiningExtractions( NeoComApiv1.NEOCOM_BACKEND_ACCEPTED_CONTENT_TYPE )
				.execute();
		if (response.isSuccessful()) {
			NeoComLogger.info( ENDPOINT_MESSAGE );
			return response.body().getMiningExtractionCount();
		} else throw new IOException( ENDPOINT_MESSAGE + " Failed." );
	}

	public Integer storeMiningExtractionEntity( final MiningExtractionEntity miningExtractionEntity ) throws IOException {
		final String ENDPOINT_MESSAGE = "Request to store a new mining extraction.";
		final Response<StoreMiningExtractionResponse> response = new Retrofit.Builder()
				.baseUrl( NeoComApiv1.NEOCOM_BACKEND_APP_HOST )
				.addConverterFactory( GSON_CONVERTER_FACTORY )
				.build()
				.create( MiningExtractionsSupportApi.class )
				.storeMiningExtraction( NeoComApiv1.NEOCOM_BACKEND_ACCEPTED_CONTENT_TYPE, miningExtractionEntity )
				.execute();
		if (response.isSuccessful()) {
			NeoComLogger.info( ENDPOINT_MESSAGE );
			return response.body().getMiningExtractionsCount();
		} else throw new IOException( ENDPOINT_MESSAGE + " Failed." );
	}
}
