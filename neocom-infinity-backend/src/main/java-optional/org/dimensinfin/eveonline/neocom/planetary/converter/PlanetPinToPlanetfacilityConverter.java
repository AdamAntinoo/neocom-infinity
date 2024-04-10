package org.dimensinfin.eveonline.neocom.planetary.converter;

import javax.validation.constraints.NotNull;

import org.dimensinfin.core.interfaces.Converter;
import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdPlanetsPlanetIdOkPins;
import org.dimensinfin.eveonline.neocom.planetary.IPlanetaryFacility;
import org.dimensinfin.eveonline.neocom.planetary.PlanetType;
import org.dimensinfin.eveonline.neocom.planetary.PlanetaryFacilityType;
import org.dimensinfin.eveonline.neocom.planetary.domain.PlanetaryFacility;
import org.dimensinfin.eveonline.neocom.service.ResourceFactory;

public class PlanetPinToPlanetfacilityConverter implements Converter<GetCharactersCharacterIdPlanetsPlanetIdOkPins, IPlanetaryFacility> {
	private final ResourceFactory resourceFactory;
	private final PlanetType planetType;

	// - C O N S T R U C T O R S
	public PlanetPinToPlanetfacilityConverter( @NotNull final ResourceFactory resourceFactory,
	                                           @NotNull final PlanetType planetType ) {
		this.resourceFactory = resourceFactory;
		this.planetType = planetType;
	}

	@Override
	public IPlanetaryFacility convert( final GetCharactersCharacterIdPlanetsPlanetIdOkPins input ) {
		final EsiType facilityItem = this.resourceFactory.generateType4Id( input.getTypeId() ); // The item for this facility.
		// Create the right facility class depending on the type.
		final PlanetaryFacilityType facilityType = PlanetaryFacilityType.getTypeByStructureGroup( facilityItem.getGroup().getGroupId() );
		final PlanetaryFacility baseFacility = new PlanetaryFacility.Builder()
				.withPin( input )
				.withPlanetType( this.planetType )
				.withFacilityItem( facilityItem )
				.build();
		switch (facilityType) {
			//					case COMMAND_CENTER:
			//						return new CommandCenterFacility.Builder()
			//								.withPlanetaryFacility(baseFacility)
			//								.withPlanetaryStorage(new PlanetaryStorage(baseFacility.getContents()))
			//								.build();
			//					case STORAGE:
			//						return new StorageFacility.Builder()
			//								.withPlanetaryFacility(baseFacility)
			//								.withPlanetaryStorage(new PlanetaryStorage(baseFacility.getContents()))
			//								.build();
			//					case LAUNCHPAD:
			//						return new StorageFacility.Builder()
			//								.withPlanetaryFacility(baseFacility)
			//								.withPlanetaryStorage(new PlanetaryStorage(baseFacility.getContents()))
			//								.build();
			//					case EXTRACTOR_CONTROL_UNIT:
			//						return new ExtractorFacility.Builder()
			//								.withPlanetaryFacility(baseFacility)
			//								.withExtractorDetails(pin)
			//								.build();
			//					case PLANETARY_FACTORY:
			//						return new FactoryFacility.Builder()
			//								.withPlanetaryFacility(baseFacility)
			//								.withSchematics(baseFacility.getSchematicId())
			//								.withPlanetaryRepository(this.planetaryRepository)
			//								.build();
		}
		return baseFacility;
	}
}
