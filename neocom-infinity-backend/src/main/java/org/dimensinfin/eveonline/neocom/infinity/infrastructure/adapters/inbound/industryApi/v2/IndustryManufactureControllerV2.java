package org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.inbound.industryApi.v2;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;
import javax.validation.constraints.NotNull;

import com.jcabi.aspects.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.dimensinfin.eveonline.neocom.industry.domain.ProcessedBlueprint;
import org.dimensinfin.eveonline.neocom.infinity.app.usecases.GetProcessedBlueprintUseCaseInput;
import org.dimensinfin.eveonline.neocom.infinity.app.usecases.ProcessedBlueprintsUseCase;
import org.dimensinfin.eveonline.neocom.infinity.config.security.NeoComAuthenticationProvider;
import org.dimensinfin.eveonline.neocom.infinity.domain.exceptions.NeoComExceptionCatalog;
import org.dimensinfin.eveonline.neocom.infinity.domain.exceptions.NeoComRuntimeHttpException;

/**
 * Industry endpoints. In use since version 0.10 but reviewed on version 0.20 and up.
 *
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since v0.29.0
 */
@RestController
@Validated
public class IndustryManufactureControllerV2 extends IndustryControllerV2 {
	private final ProcessedBlueprintsUseCase processedBlueprintsUseCase;

	// - C O N S T R U C T O R S
	@Autowired
	public IndustryManufactureControllerV2( @NotNull final NeoComAuthenticationProvider neoComAuthenticationProvider,
	                                        @NotNull final ProcessedBlueprintsUseCase processedBlueprintsUseCase ) {
		super( neoComAuthenticationProvider );
		this.processedBlueprintsUseCase = processedBlueprintsUseCase;
	}

	/**
	 * SUMMARY: Gets the list of Processed Blueprints for the selected pilot including some standard cost calculations to get the blueprint comparison
	 * profit index.
	 *
	 * Analyze the list of blueprints accessible to the selected pilot. Calculate the Bill Of Materials, the production cost and the current market
	 * cost. For variable scenarios use the pilot configuration properties to identify the preferred manufacture factory or the trading market hub.
	 * The endpoint is authenticated so validate the selected pilot identifier before going to fetch the data.
	 *
	 * v2 On this version we have removed data from the response. But Market Data is still used to calculate the manufacture and the bom costs to be
	 * compared and to obtain the comparison index. That index is the market sell profit against the manufacture cost. The value should be grater than
	 * one for profitable items. Market Data used for the initial blueprint calculation is taken from the main hub that correspond to the region where
	 * the item is located (to be completed).
	 *
	 * @param sessionid used only to dofferentiate requests and allow them to be cached differently. Session is only a intercommunication's gadget.
	 * @return the list of Blueprints along with the information required to display and help to make decisions at the front end user interface.
	 */
	@GetMapping(path = "/pilots/manufacture/blueprints/session/{sessionid}",
			consumes = "application/json",
			produces = "application/json")
	@Cacheable(lifetime = 15, unit = TimeUnit.MINUTES)
	public ResponseEntity<List<ProcessedBlueprint>> getProcessedBlueprints4Pilot( @PathVariable final String sessionid ) {
		return new ResponseEntity<>( this.processedBlueprintsUseCase.getProcessedBlueprints4Pilot(
				this.neoComAuthenticationProvider.getAuthenticatedPilot()
		), HttpStatus.OK );
	}

	@GetMapping(path = "/pilots/{pilotId}/manufacture/blueprints/{blueprintUID}",
			consumes = "application/json",
			produces = "application/json")
	@Cacheable(lifetime = 15, unit = TimeUnit.MINUTES)
	public ResponseEntity<ProcessedBlueprint> getBlueprint4Pilot4UID( @PathVariable @NotNull final Integer pilotId,
	                                                                  @PathVariable @NotNull final String blueprintUID ) {
		final UnaryOperator<GetProcessedBlueprintUseCaseInput> inputValidator = input -> {
			this.neoComAuthenticationProvider.validatePilotIdentifier( input.getPilotId() );
			if ( Objects.isNull( input.getPilotId() ) )
				throw new NeoComRuntimeHttpException( NeoComExceptionCatalog.INVALID_REQUEST_PARAMETER, "pilotId" );
			if ( Objects.isNull( input.getBlueprintUID() ) )
				throw new NeoComRuntimeHttpException( NeoComExceptionCatalog.INVALID_REQUEST_PARAMETER, "blueprintUID" );
			return input;
		};
		final Optional<ProcessedBlueprint> blueprintOptional = this.processedBlueprintsUseCase.getBlueprints4Pilot4UID(
				inputValidator.apply(
						GetProcessedBlueprintUseCaseInput.builder()
								.withPilotId( pilotId )
								.withBlueprintUID( blueprintUID )
								.build()
				)
		);
		if ( blueprintOptional.isPresent() ) return new ResponseEntity<>( blueprintOptional.get(), HttpStatus.OK );
		else throw new NeoComRuntimeHttpException( NeoComExceptionCatalog.INVALID_REQUEST_PARAMETER, blueprintUID );
	}
}
