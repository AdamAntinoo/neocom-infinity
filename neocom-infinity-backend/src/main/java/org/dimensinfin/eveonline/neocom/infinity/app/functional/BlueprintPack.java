package org.dimensinfin.eveonline.neocom.infinity.app.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.UnaryOperator;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdBlueprints200Ok;
import org.dimensinfin.logging.LogWrapper;

public class BlueprintPack implements UnaryOperator<List<GetCharactersCharacterIdBlueprints200Ok>> {
	@Override
	public List<GetCharactersCharacterIdBlueprints200Ok> apply( final List<GetCharactersCharacterIdBlueprints200Ok> blueprints ) {
		final List<GetCharactersCharacterIdBlueprints200Ok> packedBlueprints = new ArrayList<>();
		blueprints.stream()
				.forEach( blueprintDto -> {
					final Optional<GetCharactersCharacterIdBlueprints200Ok> existingBlueprint = packedBlueprints.stream()
							.filter( bp -> this.isIdentical( bp, blueprintDto ) )
							.findFirst();
					if ( existingBlueprint.isPresent() )
						existingBlueprint.get().setQuantity( existingBlueprint.get().getQuantity() + 1 );
					else {
						blueprintDto.setQuantity( 1 );
						packedBlueprints.add( blueprintDto );
					}
				} );
		return packedBlueprints;
	}

	private boolean isIdentical( final GetCharactersCharacterIdBlueprints200Ok target, final GetCharactersCharacterIdBlueprints200Ok source ) {
		LogWrapper.info( "target->" + target.toString() );
		LogWrapper.info( "source->" + source.toString() );
		final boolean identity = (Objects.equals( target.getTypeId(), source.getTypeId() )) &&
				(Objects.equals( target.getLocationId(), source.getLocationId() )) &&
				(Objects.equals( target.getMaterialEfficiency(), source.getMaterialEfficiency() )) &&
				(Objects.equals( target.getTimeEfficiency(), source.getTimeEfficiency() )) &&
				(Objects.equals( target.getRuns(), source.getRuns() ));
		LogWrapper.info( "identity->" + identity );
		return (Objects.equals( target.getTypeId(), source.getTypeId() )) &&
				(Objects.equals( target.getLocationId(), source.getLocationId() )) &&
				(Objects.equals( target.getMaterialEfficiency(), source.getMaterialEfficiency() )) &&
				(Objects.equals( target.getTimeEfficiency(), source.getTimeEfficiency() )) &&
				(Objects.equals( target.getRuns(), source.getRuns() ));
	}
}
