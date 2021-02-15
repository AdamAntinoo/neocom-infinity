package org.dimensinfin.eveonline.neocom.infinity.backend.corporation.rest.v1;

import java.io.IOException;
import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.asset.domain.LocationAssetContainer;
import org.dimensinfin.eveonline.neocom.domain.Corporation;
import org.dimensinfin.eveonline.neocom.infinity.backend.corporation.domain.ShippingYardLocation;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.ErrorInfo;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComAuthorizationException;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComController;

@RestController
@Validated
@RequestMapping("/api/v1/neocom")
public class CorporationControllerV1 extends NeoComController {
	private final CorporationServiceV1 corporationServiceV1;
	private final NeoComAuthenticationProvider neoComAuthenticationProvider;

	// - C O N S T R U C T O R S
	@Autowired
	public CorporationControllerV1( final CorporationServiceV1 corporationServiceV1,
	                                final NeoComAuthenticationProvider neoComAuthenticationProvider ) {
		this.corporationServiceV1 = corporationServiceV1;
		this.neoComAuthenticationProvider = neoComAuthenticationProvider;
	}

	/**
	 * gets the list of corporations assets and using containers stores the results depending on the asset parent location. The first level is the
	 * asset system location because constellations and regions can be inferred from that data.
	 * Assets are returned on a parent-child hierarchy starting at the system or station, then the container or ship and then the asset.
	 *
	 * @param corporationId unique identifier for the corporation requested.
	 * @return the hierarchy of assets classified by their systems location.
	 */
	@GetMapping(path = "/corporations/{corporationId}/assetsbylocation",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<List<LocationAssetContainer>> getCorporationAssetsByLocation( @PathVariable @NotNull final Integer corporationId ) {
		// Check corporation identifier access is authorized.
		try {
			final Integer authorizedCorporationId = this.neoComAuthenticationProvider.getAuthenticatedCorporation();
			if (authorizedCorporationId.intValue() != corporationId.intValue())
				throw new NeoComAuthorizationException( ErrorInfo.CORPORATION_ID_NOT_AUTHORIZED );
		} catch (final IOException ioe) {
			throw new NeoComAuthorizationException( ErrorInfo.CORPORATION_ID_NOT_AUTHORIZED );
		}
		return this.corporationServiceV1.getCorporationAssetsByLocation( corporationId );
	}

	/**
	 * Return the corporation public data received from the ESI server. Before accessing the data check that the requester is authorized to get
	 * this data.
	 *
	 * @param corporationId unique identifier for the corporation requested.
	 */
	@GetMapping(path = "/corporations/{corporationId}",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<Corporation> getCorporationData( @PathVariable final Integer corporationId ) {
		// Check corporation identifier access is authorized.
		try {
			final Integer authorizedCorporationId = this.neoComAuthenticationProvider.getAuthenticatedCorporation();
			if (authorizedCorporationId.intValue() != corporationId.intValue())
				throw new NeoComAuthorizationException( ErrorInfo.CORPORATION_ID_NOT_AUTHORIZED );
		} catch (final IOException ioe) {
			throw new NeoComAuthorizationException( ErrorInfo.CORPORATION_ID_NOT_AUTHORIZED );
		}
		return this.corporationServiceV1.getCorporationData( corporationId );
	}

	/**
	 * Gets the list of configured or available ship yard locations. Ships yards are the selected places where to deploy components to allow the
	 * start of a ship construction industry job.
	 *
	 * @param corporationId the corporation identifier to be used to get the list of shipping yards locations.
	 */
	@GetMapping(path = "/corporations/{corporationId}/shippingyards",
			consumes = "application/json",
			produces = "application/json")
	public ResponseEntity<List<ShippingYardLocation>> getCorporationShippingYards( @PathVariable final Integer corporationId ) {
		// Check corporation identifier access is authorized.
		try {
			final Integer authorizedCorporationId = this.neoComAuthenticationProvider.getAuthenticatedCorporation();
			if (authorizedCorporationId.intValue() != corporationId.intValue())
				throw new NeoComAuthorizationException( ErrorInfo.CORPORATION_ID_NOT_AUTHORIZED );
		} catch (final IOException ioe) {
			throw new NeoComAuthorizationException( ErrorInfo.CORPORATION_ID_NOT_AUTHORIZED );
		}
		return this.corporationServiceV1.getCorporationShippingYards( corporationId );
	}
}
