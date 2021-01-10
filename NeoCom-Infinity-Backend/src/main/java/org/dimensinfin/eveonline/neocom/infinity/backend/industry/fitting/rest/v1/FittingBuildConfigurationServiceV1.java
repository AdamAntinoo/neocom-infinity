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

import org.dimensinfin.eveonline.neocom.domain.FittingV2;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdFittings200Ok;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.converter.GetCharactersCharacterIdFittingsToFittingV2Converter;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingBuildConfiguration;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.domain.FittingConfigurations;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.persistence.ActionPreferenceEntity;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.persistence.BuildActionPreferencesRepository;
import org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.service.FittingBuildConfigurationGenerator;
import org.dimensinfin.eveonline.neocom.infinity.config.security.CredentialDetailsService;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComError;
import org.dimensinfin.eveonline.neocom.infinity.core.exception.NeoComRuntimeBackendException;
import org.dimensinfin.eveonline.neocom.infinity.core.rest.NeoComCredentialService;
import org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.processor.IndustryBuildProcessor;
import org.dimensinfin.eveonline.neocom.service.ESIDataService;
import org.dimensinfin.eveonline.neocom.service.LocationCatalogService;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;

import static org.dimensinfin.eveonline.neocom.infinity.backend.industry.IndustryControllerV1.INDUSTRY_ERROR_CODE_PREFIX;

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

	private final ESIDataService esiDataService;
	private final LocationCatalogService locationCatalogService;
	private final BuildActionPreferencesRepository buildActionPreferencesRepository;
	private final ResourceFactory resourceFactory;

	// - C O N S T R U C T O R S
	public FittingBuildConfigurationServiceV1( final @NotNull NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                           final @NotNull CredentialDetailsService credentialDetailsService,
	                                           final @NotNull ESIDataService esiDataService,
	                                           final @NotNull LocationCatalogService locationCatalogService,
	                                           final @NotNull BuildActionPreferencesRepository buildActionPreferencesRepository,
	                                           final @NotNull ResourceFactory resourceFactory ) {
		super( neoComAuthenticationProvider, credentialDetailsService );
		this.esiDataService = esiDataService;
		this.locationCatalogService = Objects.requireNonNull( locationCatalogService );
		this.buildActionPreferencesRepository = buildActionPreferencesRepository;
		this.resourceFactory = resourceFactory;
	}

	public FittingBuildConfiguration getFittingBuildConfigurationSavedConfiguration( final @NotNull Integer fittingId ) {
		return fittingConfiguration( fittingId, true );
	}

	public FittingBuildConfiguration getFittingBuildConfigurationTargetConfiguration( final @NotNull Integer fittingId ) {
		return fittingConfiguration( fittingId, false );
	}

	public FittingConfigurations getFittingConfigurations( final @NotNull Integer fittingId ) {
		this.getAuthorizedCredential();
		return new FittingConfigurations.Builder()
				.withSavedLink(
						WebMvcLinkBuilder.linkTo(
								WebMvcLinkBuilder.methodOn( FittingBuildConfigurationControllerV1.class )
										.getFittingBuildConfigurationSavedConfiguration( fittingId )
						).withRel( "saved" )
				)
				.withTargetLink(
						WebMvcLinkBuilder.linkTo(
								WebMvcLinkBuilder.methodOn( FittingBuildConfigurationControllerV1.class )
										.getFittingBuildConfigurationTargetConfiguration( fittingId )
						).withRel( "target" )
				)
				.build();
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
	private FittingBuildConfiguration fittingConfiguration( final @NotNull int fittingId, final boolean saved ) {
		// Get the list of fitting for the logged Pilot from the authenticated Credential.
		final List<GetCharactersCharacterIdFittings200Ok> fittingList = this.esiDataService.getCharactersCharacterIdFittings(
				this.getAuthorizedCredential()
		);
		// Search for the target fitting identifier received as the parameter.
		// Search for matching fitting
		final FittingV2 targetFitting = new GetCharactersCharacterIdFittingsToFittingV2Converter( this.resourceFactory )
				.convert( fittingList.stream()
						.filter( fitting -> fitting.getFittingId() == fittingId ) // Search for matching fitting
						.collect( this.toSingleton( fittingId ) )
				);
		// Get the list of preferences for this precise fitting. If none means that this is a new Order instance.
		final String actionFilter = FittingBuildConfiguration.uniqueIdConstructor( this.getAuthorizedCredential().getAccountId(), fittingId );
		final List<ActionPreferenceEntity> actionPreferences = this.buildActionPreferencesRepository.findAll().stream()
				.filter( action -> action.getFittingConfigurationId().equalsIgnoreCase( actionFilter ) )
				.collect( Collectors.toList() );

		// Instantiate a processor to generate the required actions to complete the job.
		final IndustryBuildProcessor industryBuildProcessor = new IndustryBuildProcessor.Builder()
				.withCredential( this.getAuthorizedCredential() )
				.withEsiDataService( this.esiDataService )
				.withLocationCatalogService( this.locationCatalogService )
				.build();

		final FittingBuildConfigurationGenerator transformer = new FittingBuildConfigurationGenerator.Builder()
				.withIndustryBuildProcessor( industryBuildProcessor )
				.withFitting( targetFitting )
				.withPreferences( actionPreferences )
				.build();
		return saved ?
				transformer.transformInitialState( this.getAuthorizedCredential().getAccountId() ) :
				transformer.transformTargetState( this.getAuthorizedCredential().getAccountId() );
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
