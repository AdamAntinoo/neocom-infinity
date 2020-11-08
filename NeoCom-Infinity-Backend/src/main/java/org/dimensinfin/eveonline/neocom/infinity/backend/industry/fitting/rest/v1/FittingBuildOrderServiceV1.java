package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.rest.v1;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.dimensinfin.eveonline.neocom.adapter.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdFittings200Ok;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.LocationCatalogServiceWrapper;
import org.dimensinfin.eveonline.neocom.infinity.backend.core.rest.NeoComCredentialService;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain.FittingIndustryJob;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.persistence.ActionPreferenceEntity;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.persistence.BuildActionPreferencesRepository;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.rest.dao.FittingBuildOrderDao;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.service.FittingBuildTransformationService;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComError;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.processor.IndustryBuildProcessor;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

/**
 * Fitting Build Orders are a complex data structure that represents the components and actions that that user preferences when building a determinate
 * fit. Orders have a unique identifier that is composed from the pilot unique identifier and the fitting unique identifier. This way the fitting
 * preferences can be stored for a fitting avoiding the creation of duplicates on future fitting preferences changes.
 *
 * Orders have an initial state when they are not found at the repository. On creation the list of preferences is empty and applied to the Order
 * Transformer generate buy actions for all the fit components including the hull. This is a starting point that can be modified adding preferences
 * with other endpoints.
 */
@Service
public class FittingBuildOrderServiceV1 extends NeoComCredentialService {
	private static final String BUILD_FITTING_ACTION_PREFIX = "FB";

	public static NeoComError error_FITTINGSEARCHRETURNEDFAILURE( final String message ) {
		return new NeoComError.Builder()
				.withErrorName( "FITTING_SEARCH_FAILURE" )
				.withHttpStatus( HttpStatus.NOT_FOUND )
				.withErrorCode( "dimensinfin.neocom.industry.fitting.mismatch" )
				.withMessage( message )
				.build();
	}

	public static NeoComError error_FITTINGREQUESTNOTAUTHORIZED() {
		return new NeoComError.Builder()
				.withErrorName( "FITTING_UNAUTHORIZED" )
				.withHttpStatus( HttpStatus.UNAUTHORIZED )
				.withErrorCode( "dimensinfin.neocom.industry.fitting.notauthorized" )
				.withMessage( "Fitting request not authorized." )
				.build();
	}

	private final ESIDataProvider esiDataProvider;
	private final LocationCatalogService locationCatalogService;
	private final BuildActionPreferencesRepository buildActionPreferencesRepository;

	// - C O N S T R U C T O R S
	public FittingBuildOrderServiceV1( final @NotNull NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                   final @NotNull CredentialDetailsService credentialDetailsService,
	                                   final @NotNull ESIDataProviderWrapper esiDataProviderWrapper,
	                                   final @NotNull LocationCatalogServiceWrapper locationCatalogServiceWrapper,
	                                   final @NotNull BuildActionPreferencesRepository buildActionPreferencesRepository ) {
		super( null, neoComAuthenticationProvider, credentialDetailsService );
		this.esiDataProvider = Objects.requireNonNull( esiDataProviderWrapper.getSingleton() );
		this.locationCatalogService = Objects.requireNonNull( locationCatalogServiceWrapper.getSingleton() );
		this.buildActionPreferencesRepository = buildActionPreferencesRepository;
	}

	/**
	 * Generate the Fitting initial transformation and apply the list of preferences found at the repository.
	 *
	 * To get the final Fitting Build Order the list of steps is the next:
	 * <ol>
	 *     <li>Get the list of fitting for the logged Pilot from the authenticated Credential.</li>
	 *     <li>Search for the target fitting identifier received as the parameter.</li>
	 *     <li>Get the list of preferences for this precise fitting. If none means that this is a new Order instance.</li>
	 *     <li>Apply the list of preferences to the to the target fitting job build process.</li>
	 *     <li>If required convert the result before sending it back to the caller.</li>
	 * </ol>
	 *
	 * @param fittingId the fitting unique identifier for the target fitting. Used to compose the unique order identifier
	 */
	public FittingBuildOrderDao getFittingBuildOrderById( final @NotNull Integer fittingId ) {
		if (null != this.getAuthorizedCredential())
			return new FittingBuildOrderDao.Builder()
					.withSavedLink(
							WebMvcLinkBuilder.linkTo(
									WebMvcLinkBuilder.methodOn( FittingBuildOrderControllerV1.class )
											.getFittingBuildOrderSavedConfiguration( fittingId )
							).withSelfRel()
					)
					.withTargetLink(
							WebMvcLinkBuilder.linkTo(
									WebMvcLinkBuilder.methodOn( FittingBuildOrderControllerV1.class )
											.getFittingBuildOrderTargetConfiguration( fittingId )
							).withSelfRel()
					)
					.build();
		else throw new NeoComRuntimeBackendException( error_FITTINGREQUESTNOTAUTHORIZED() );
	}

	public FittingIndustryJob getFittingBuildOrderSavedConfiguration( final @NotNull Integer fittingId ) {
		return fittingConfiguration( fittingId );
	}

	public FittingIndustryJob getFittingBuildOrderTargetConfiguration( final @NotNull Integer fittingId ) {
		return fittingConfiguration( fittingId );
	}

	private FittingIndustryJob fittingConfiguration( final @NotNull int fittingId ) {
		// Get the list of fitting for the logged Pilot from the authenticated Credential.
		final List<GetCharactersCharacterIdFittings200Ok> fittingList = this.esiDataProvider.getCharactersCharacterIdFittings(
				this.getAuthorizedCredential()
		);
		// Search for the target fitting identifier received as the parameter.
		final GetCharactersCharacterIdFittings200Ok targetFitting = fittingList.stream()
				.filter( fitting -> fitting.getFittingId() == fittingId ) // Search for matching fitting
				.collect( this.toSingleton( fittingId ) );
		// Get the list of preferences for this precise fitting. If none means that this is a new Order instance.
		final List<ActionPreferenceEntity> actionPreferences = this.buildActionPreferencesRepository.findAll().stream()
				.filter( action -> ActionPreferenceEntity.uniqueIdConstructor(
						BUILD_FITTING_ACTION_PREFIX,
						this.getAuthorizedCredential().getAccountId(),
						fittingId
				).equalsIgnoreCase( action.getId() ) )
				.collect( Collectors.toList() );

		// Instantiate a processor to generate the required actions to complete the job.
		final IndustryBuildProcessor industryBuildProcessor = new IndustryBuildProcessor.Builder()
				.withCredential( this.getAuthorizedCredential() )
				.withEsiDataProvider( this.esiDataProvider )
				.withLocationCatalogService( this.locationCatalogService )
				.build();

		final FittingBuildTransformationService transformer = new FittingBuildTransformationService.Builder()
				.withIndustryBuildProcessor( industryBuildProcessor )
				.withFitting( targetFitting )
				.withPreferences( actionPreferences )
				.build();
		return transformer.transformInitialState();
	}

	/**
	 * Check that only a single fitting and no less than a fitting matches the target fitting identifier.
	 */
	private <T> Collector<T, ?, T> toSingleton( final int fittingId ) throws NeoComRuntimeBackendException {
		return Collectors.collectingAndThen(
				Collectors.toList(),
				list -> {
					if (list.isEmpty())
						throw new NeoComRuntimeBackendException( error_FITTINGSEARCHRETURNEDFAILURE(
								MessageFormat.format( "There are more than one fitting matching the requested id {0}", fittingId )
						) );
					if (1 != list.size())
						throw new NeoComRuntimeBackendException( error_FITTINGSEARCHRETURNEDFAILURE(
								MessageFormat.format( "There are no fitting matching the requested id {0}", fittingId )
						) );
					return list.get( 0 );
				}
		);
	}
}
