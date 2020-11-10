package org.dimensinfin.eveonline.neocom.infinity.backend.industry.rest.v1;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.adapter.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.esiswagger.model.CharacterscharacterIdfittingsItems;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdFittings200Ok;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.LocationCatalogServiceWrapper;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.rest.v2.PilotFittingsServiceV2;
import org.dimensinfin.eveonline.neocom.infinity.backend.core.rest.NeoComCredentialService;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain.FittingIndustryJob;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain.ItemFactory;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.processor.IndustryBuildProcessor;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

@Service
public class FittingTransformationServiceV1 extends NeoComCredentialService {
	private ESIDataProvider esiDataProvider;
	private LocationCatalogService locationCatalogService;

	// - C O N S T R U C T O R S
	public FittingTransformationServiceV1( final @NotNull ESIDataProviderWrapper esiDataProviderWrapper,
	                                       final @NotNull NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                       final @NotNull CredentialDetailsService credentialDetailsService,
	                                       final @NotNull LocationCatalogServiceWrapper locationCatalogServiceWrapper ) {
		super( neoComAuthenticationProvider, credentialDetailsService );
		this.esiDataProvider = Objects.requireNonNull( esiDataProviderWrapper.getSingleton() );
		this.locationCatalogService = Objects.requireNonNull( locationCatalogServiceWrapper.getSingleton() );
	}

	/**
	 * For a new fitting identifier the service should search for a industry job to get one copy of this fitting completely manufactured but where
	 * all the actions required to complete the fit are completed by buying the items on the market.
	 *
	 * The market selected depends on the preferred market hub for the corporation region. And the corporation region is obtained from the
	 * Corporation home location for the logged Pilot.
	 *
	 * So the first action should be to get the Credential. It should be a valid credential from the authorization header.
	 * With that information we should instantiate a Industry Processor. Then we request this processor to generate the buy manufacture job that
	 * should contain the time and money values required to complete a copy of that fitting identifier.
	 *
	 * @param fittingId the target fitting to build. This fitting should be extracted from the list of available fitting to the logged Pilot that
	 *                  is also obtained from the authenticated Credential.
	 * @return the Dao data conversion for the industry job.
	 */
	public FittingIndustryJob getFittingMarketCost( final int fittingId ) {
		// Get the list of fittings available to this Pilot.
		final List<GetCharactersCharacterIdFittings200Ok> fittingList = this.esiDataProvider.getCharactersCharacterIdFittings(
				this.getAuthorizedCredential()
		);
		// Instantiate a processor to generate the required actions to complete the job.
		final IndustryBuildProcessor industryBuildProcessor = new IndustryBuildProcessor.Builder()
				.withCredential( this.getAuthorizedCredential() )
				.withEsiDataProvider( this.esiDataProvider )
				.withLocationCatalogService( this.locationCatalogService )
				.build();
		final GetCharactersCharacterIdFittings200Ok targetFitting = fittingList.stream()
				.filter( fitting -> fitting.getFittingId() == fittingId ) // Search for matching fitting
				.collect( this.toSingleton( fittingId ) );
		final FittingIndustryJob fittingJob = new FittingIndustryJob.Builder()
				.withHull( industryBuildProcessor.generateBuyAction( targetFitting.getShipTypeId(), 1 ) )
				.build(); // Create a new industry job.
		for (CharacterscharacterIdfittingsItems fittingItem : targetFitting.getItems()) {
			fittingJob.addJobActionToBom(
					industryBuildProcessor.generateBuyAction( fittingItem.getTypeId(), fittingItem.getQuantity() )
			);
		}
		return fittingJob;
	}

	private <T> Collector<T, ?, T> toSingleton( final int fittingId ) {
		return Collectors.collectingAndThen(
				Collectors.toList(),
				list -> {
					if (list.size() != 1)
						throw new IllegalStateException(
								MessageFormat.format( "There are more than one fitting matching the request id {0}", fittingId )
						);
					return list.get( 0 );
				}
		);
	}
}
