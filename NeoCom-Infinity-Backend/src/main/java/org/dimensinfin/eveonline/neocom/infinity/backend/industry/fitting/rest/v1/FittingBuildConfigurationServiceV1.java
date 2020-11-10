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
import org.dimensinfin.eveonline.neocom.domain.Fitting;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdFittings200Ok;
import org.dimensinfin.eveonline.neocom.infinity.adapter.ESIDataProviderWrapper;
import org.dimensinfin.eveonline.neocom.infinity.adapter.LocationCatalogServiceWrapper;
import org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.converter.EsiCharacterFittingToFittingModelConverter;
import org.dimensinfin.eveonline.neocom.infinity.backend.core.rest.NeoComCredentialService;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.domain.FittingIndustryJob;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.persistence.ActionPreferenceEntity;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.persistence.BuildActionPreferencesRepository;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.rest.dao.FittingBuildConfigurationDao;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.service.FittingBuildTransformationService;
import org.dimensinfin.eveonline.neocom.infinity.backend.universe.item.rest.v2.EsiItemServiceV2;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComError;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.core.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.core.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.processor.IndustryBuildProcessor;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;

import static org.dimensinfin.eveonline.neocom.infinity.backend.industry.IndustryController.INDUSTRY_ERROR_CODE_PREFIX;

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
public class FittingBuildConfigurationServiceV1 extends NeoComCredentialService {
	private static final String BUILD_FITTING_ACTION_PREFIX = "FB";

	public static NeoComError Error_FITTINGSEARCHRETURNEDFAILURE( final String message ) {
		return new NeoComError.Builder()
				.withErrorName( "FITTING_SEARCH_FAILURE" )
				.withHttpStatus( HttpStatus.NOT_FOUND )
				.withErrorCode( INDUSTRY_ERROR_CODE_PREFIX + ".fitting.mismatch" )
				.withMessage( message )
				.build();
	}

	//	public static NeoComError Error_FITTINGREQUESTNOTAUTHORIZED( final String message ) {
	//		return new NeoComError.Builder()
	//				.withErrorName( "FITTING_UNAUTHORIZED" )
	//				.withHttpStatus( HttpStatus.UNAUTHORIZED )
	//				.withErrorCode( INDUSTRY_ERROR_CODE_PREFIX + ".fitting.notauthorized" )
	//				.withMessage( MessageFormat.format( "Fitting request not authorized. {0}", message ) )
	//				.withCause( "Not found a valid Credential to authenticate the request." )
	//				.build();
	//	}

	private final ESIDataProvider esiDataProvider;
	private final LocationCatalogService locationCatalogService;
	private final BuildActionPreferencesRepository buildActionPreferencesRepository;
	private final EsiItemServiceV2 esiItemServiceV2;

	// - C O N S T R U C T O R S
	public FittingBuildConfigurationServiceV1( final @NotNull NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                           final @NotNull CredentialDetailsService credentialDetailsService,
	                                           final @NotNull ESIDataProviderWrapper esiDataProviderWrapper,
	                                           final @NotNull LocationCatalogServiceWrapper locationCatalogServiceWrapper,
	                                           final @NotNull BuildActionPreferencesRepository buildActionPreferencesRepository,
	                                           final @NotNull EsiItemServiceV2 esiItemServiceV2 ) {
		super( neoComAuthenticationProvider, credentialDetailsService );
		this.esiDataProvider = Objects.requireNonNull( esiDataProviderWrapper.getSingleton() );
		this.locationCatalogService = Objects.requireNonNull( locationCatalogServiceWrapper.getSingleton() );
		this.buildActionPreferencesRepository = buildActionPreferencesRepository;
		this.esiItemServiceV2 = esiItemServiceV2;
	}

	public FittingBuildConfigurationDao getFittingBuildConfigurationById( final @NotNull Integer fittingId ) {
		//		try {
		this.getAuthorizedCredential();
		return new FittingBuildConfigurationDao.Builder()
				.withSavedLink(
						WebMvcLinkBuilder.linkTo(
								WebMvcLinkBuilder.methodOn( FittingBuildConfigurationControllerV1.class )
										.getFittingBuildConfigurationSavedConfiguration( fittingId )
						).withSelfRel()
				)
				.withTargetLink(
						WebMvcLinkBuilder.linkTo(
								WebMvcLinkBuilder.methodOn( FittingBuildConfigurationControllerV1.class )
										.getFittingBuildConfigurationTargetConfiguration( fittingId )
						).withSelfRel()
				)
				.build();
		//		} catch (final NeoComRuntimeBackendException ncrex) {
		//			throw new NeoComRuntimeBackendException( Error_FITTINGREQUESTNOTAUTHORIZED(ncrex.getMessage()) );
		//		}
	}

	public FittingIndustryJob getFittingBuildConfigurationSavedConfiguration( final @NotNull Integer fittingId ) {
		return fittingConfiguration( fittingId, true );
	}

	public FittingIndustryJob getFittingBuildConfigurationTargetConfiguration( final @NotNull Integer fittingId ) {
		return fittingConfiguration( fittingId, false );
	}

	/**
	 * Generate the Fitting initial transformation and apply the list of preferences found at the repository on a saved state.
	 *
	 * To get the final Fitting Build Configuration the list of steps is the next:
	 * <ol>
	 *     <li>Get the list of fitting for the logged Pilot from the authenticated Credential.</li>
	 *     <li>Search for the target fitting identifier received as the parameter.</li>
	 *     <li>Get the list of preferences for this precise fitting that are marked as SAVED. If none means that this is a new Order instance.</li>
	 *     <li>Apply the list of preferences to the target fitting build process.</li>
	 *     <li>If required convert the result before sending it back to the caller.</li>
	 * </ol>
	 *
	 * @param fittingId the fitting unique identifier for the target fitting. Used to compose the unique configuration identifier
	 */
	private FittingIndustryJob fittingConfiguration( final @NotNull int fittingId, final boolean saved ) {
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
						fittingId ).equalsIgnoreCase( action.getId() )
				)
				.collect( Collectors.toList() );

		// Instantiate a processor to generate the required actions to complete the job.
		final IndustryBuildProcessor industryBuildProcessor = new IndustryBuildProcessor.Builder()
				.withCredential( this.getAuthorizedCredential() )
				.withEsiDataProvider( this.esiDataProvider )
				.withLocationCatalogService( this.locationCatalogService )
				.build();

		final FittingBuildTransformationService transformer = new FittingBuildTransformationService.Builder()
				.withIndustryBuildProcessor( industryBuildProcessor )
				.withFitting(
						new EsiCharacterFittingToFittingModelConverter(  this.esiDataProvider, this.esiItemServiceV2).convert( targetFitting ))
				.withPreferences( actionPreferences )
				.build();
		return saved ? transformer.transformInitialState() : transformer.transformTargetState();
	}

	/**
	 * Check that only a single fitting and no less than a fitting matches the target fitting identifier.
	 */
	private <T> Collector<T, ?, T> toSingleton( final int fittingId ) {
		return Collectors.collectingAndThen(
				Collectors.toList(),
				list -> {
					if (list.isEmpty())
						throw new NeoComRuntimeBackendException( Error_FITTINGSEARCHRETURNEDFAILURE(
								MessageFormat.format( "There are more than one fitting matching the requested id {0}", fittingId )
						) );
					if (1 != list.size())
						throw new NeoComRuntimeBackendException( Error_FITTINGSEARCHRETURNEDFAILURE(
								MessageFormat.format( "There are no fitting matching the requested id {0}", fittingId )
						) );
					return list.get( 0 );
				}
		);
	}
}
