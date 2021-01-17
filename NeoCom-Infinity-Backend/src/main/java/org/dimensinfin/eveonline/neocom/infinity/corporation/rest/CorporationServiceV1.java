package org.dimensinfin.eveonline.neocom.infinity.corporation.rest;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.asset.domain.LocationAssetContainer;
import org.dimensinfin.eveonline.neocom.asset.service.AssetProvider;
import org.dimensinfin.eveonline.neocom.asset.service.NetworkAssetSource;
import org.dimensinfin.eveonline.neocom.database.entities.Credential;
import org.dimensinfin.eveonline.neocom.database.entities.NeoAsset;
import org.dimensinfin.eveonline.neocom.database.repositories.AssetRepository;
import org.dimensinfin.eveonline.neocom.database.repositories.CredentialRepository;
import org.dimensinfin.eveonline.neocom.domain.Corporation;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;
import org.dimensinfin.eveonline.neocom.domain.space.Station;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCorporationsCorporationIdOk;
import org.dimensinfin.eveonline.neocom.infinity.adapter.AssetRepositoryWrapper;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.pilot.rest.v1.PilotServiceV1;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetails;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.ErrorInfo;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComNotFoundException;
import org.dimensinfin.eveonline.neocom.infinity.corporation.domain.ShippingYardLocation;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.LocationCatalogService;
import org.dimensinfin.logging.LogWrapper;

@Service
public class CorporationServiceV1 {
	private static final String SHIPPING_YARD_PREFIX = "ShipYard";
	private final ESIDataService esiDataService;
	private final PilotServiceV1 pilotServiceV1;
	private final AssetRepository assetRepository;
	private final CredentialRepository credentialRepository;
	private final LocationCatalogService locationCatalogService;
	private final CredentialDetailsService credentialDetailsService;
	private final NeoComAuthenticationProvider neoComAuthenticationProvider;

	// - C O N S T R U C T O R S
	@Autowired
	public CorporationServiceV1( @NotNull final ESIDataService esiDataService,
	                             @NotNull final PilotServiceV1 pilotServiceV1,
	                             @NotNull final AssetRepositoryWrapper assetRepositoryWrapper,
	                             @NotNull final CredentialRepository credentialRepository,
	                             @NotNull final LocationCatalogService locationCatalogService,
	                             @NotNull final CredentialDetailsService credentialDetailsService,
	                             @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider ) {
		this.esiDataService = esiDataService;
		this.pilotServiceV1 = pilotServiceV1;
		this.assetRepository = assetRepositoryWrapper.getSingleton();
		this.credentialRepository = credentialRepository;
		this.locationCatalogService = locationCatalogService;
		this.credentialDetailsService = credentialDetailsService;
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
	}

	public ResponseEntity<List<LocationAssetContainer>> getCorporationAssetsByLocation( final Integer corporationId ) {
		final String uniqueId = this.neoComAuthenticationProvider.getAuthenticatedUniqueId();
		final Credential credential = ((CredentialDetails) this.credentialDetailsService.loadUserByUsername( uniqueId )).getCredential();
		final AssetProvider assetProvider = new AssetProvider.Builder()
				.withCredential( credential )
				.withAssetRepository( this.assetRepository )
				.withLocationCatalogService( this.locationCatalogService )
				.optionalAssetSource( new NetworkAssetSource.Builder()
						.withCredential( credential )
						.withAssetRepository( this.assetRepository )
						.withCredentialRepository( this.credentialRepository )
						.withEsiDataProvider( this.esiDataService )
						.withLocationCatalogService( this.locationCatalogService )
						.build() )
				.build();
		final List<LocationAssetContainer> assetHierarchy = assetProvider.classifyCorporationAssetsByLocation( corporationId );
		return new ResponseEntity<>( assetHierarchy, HttpStatus.OK );
	}

	public ResponseEntity<Corporation> getCorporationData( final int corporationId ) {
		final GetCorporationsCorporationIdOk corporationData = this.esiDataService.getCorporationsCorporationId( corporationId );
		if (null == corporationData)
			throw new NeoComNotFoundException( ErrorInfo.TARGET_NOT_FOUND, "Pilot", Integer.toString( corporationId ) );
		return new ResponseEntity<>( this.obtainCorporationData( corporationId ), HttpStatus.OK );
	}

	public ResponseEntity<List<ShippingYardLocation>> getCorporationShippingYards( final Integer corporationId ) {
		final String uniqueId = this.neoComAuthenticationProvider.getAuthenticatedUniqueId();
		final Credential credential = ((CredentialDetails) this.credentialDetailsService.loadUserByUsername( uniqueId )).getCredential();
		// Get the list of corporation assets and filter out the shipping yards.
		final AssetProvider assetProvider = new AssetProvider.Builder()
				.withCredential( credential )
				.withAssetRepository( this.assetRepository )
				.withLocationCatalogService( this.locationCatalogService )
				.optionalAssetSource( new NetworkAssetSource.Builder()
						.withCredential( credential )
						.withAssetRepository( this.assetRepository )
						.withCredentialRepository( this.credentialRepository )
						.withEsiDataProvider( this.esiDataService )
						.withLocationCatalogService( this.locationCatalogService )
						.build() )
				.build();
		//		final List<NeoAsset> assetList = new AssetDownloadProcessorJob.Builder()
		//				.withCredential( credential )
		//				.withAssetRepository( this.assetRepository )
		//				.withCredentialRepository( this.credentialRepository )
		//				.withEsiDataProvider( this.esiDataProvider )
		//				.withLocationCatalogService( this.locationCatalogService )
		//				.build()
		//				.downloadCorporationAssets( corporationId );
		final List<NeoAsset> assetList = assetProvider.downloadCorporationAssets( corporationId );
		final List<ShippingYardLocation> yards = new ArrayList<>();
		for (final NeoAsset asset : assetList) {
			if (null != asset.getUserLabel()) {
				// Only detect yards that have it identified on the name.
				if (null != asset.getUserLabel())
					if (asset.getUserLabel().startsWith( SHIPPING_YARD_PREFIX )) {
						// Search on the list of assets for the asset related to the office container.
						final NeoAsset officeContainer = this.search4AssetId( asset.getParentContainerId(), assetList );
						if (null != officeContainer) {
							final SpaceLocation location = this.locationCatalogService.searchLocation4Id(
									officeContainer.getLocationId().getSpaceIdentifier() );
							if (location instanceof Station) {
								final Station station = (Station) location;
								final ShippingYardLocation yard = new ShippingYardLocation.Builder()
										.withYardDeposit( asset )
										.withOfficeContainer( officeContainer )
										.withStation( station )
										.build();
								yards.add( yard );
							}
						}
					}
				// Check if this asset is located on Corporation premises.
				LogWrapper.info( MessageFormat.format( "Yard found: {0} - {1}", asset.getName(), asset.getUserLabel() ) );
				LogWrapper.info( MessageFormat.format( "Location data: {0}", asset.getLocationId().getType().name() ) );
			}
		}
		return new ResponseEntity<>( yards, HttpStatus.OK );
	}

	public Corporation obtainCorporationData( final int corporationId ) {
		final GetCorporationsCorporationIdOk corporationData = this.esiDataService.getCorporationsCorporationId( corporationId );
		// Access the rest of the corporation's esi data from the service.
		final Corporation.Builder corporationBuilder = new Corporation.Builder()
				.withCorporationId( corporationId )
				.withCorporationPublicData( corporationData )
				.withCeoPilotData( this.pilotServiceV1.buildPilotData( corporationData.getCeoId() ) );
		if (null != corporationData.getAllianceId())
			return corporationBuilder.optionslAlliance(
					this.esiDataService.getAlliancesAllianceId( corporationData.getAllianceId() ) )
					.build();
		else return corporationBuilder.build();
	}

	private NeoAsset search4AssetId( final Long containerId, final List<NeoAsset> assetList ) {
		for (final NeoAsset asset : assetList)
			if (asset.getAssetId().equals( containerId )) return asset;
		return null;
	}
}
