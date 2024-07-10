package org.dimensinfin.eveonline.neocom.infinity.app.usecases;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "with")
public class GetProcessedBlueprintUseCaseInput {
	/**
	 * pilotId the pilot unique identifier.
	 */
	private Integer pilotId;
	/**
	 * the blueprint UID to be located. Blueprints have a UID that is composed by the Pilot, the Region and the Type identifiers.
	 */
	private String blueprintUID;
}
