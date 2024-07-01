package org.dimensinfin.eveonline.neocom.infinity.backend.scheduler.jobs;

import java.util.Optional;

import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdBlueprints200Ok;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BlueprintAndLocation {
	private GetCharactersCharacterIdBlueprints200Ok blueprint;
	private Optional<SpaceLocation> location;
}

