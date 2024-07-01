package org.dimensinfin.eveonline.neocom.infinity.app.functional;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;

/**
 * Converts nullables results to Optional until this chages are merged on the new DataManagement library.
 */
public class LocationLookup implements Function<SpaceLocation, Optional<SpaceLocation>> {

	@Override
	public Optional<SpaceLocation> apply( final SpaceLocation spaceLocation ) {
		return (Objects.isNull( spaceLocation ) ? Optional.empty() : Optional.of( spaceLocation ));
	}
}
