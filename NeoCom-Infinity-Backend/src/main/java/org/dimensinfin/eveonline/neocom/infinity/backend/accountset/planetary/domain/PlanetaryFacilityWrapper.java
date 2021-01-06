package org.dimensinfin.eveonline.neocom.infinity.backend.accountset.planetary.domain;

import java.util.List;

import org.joda.time.DateTime;

import org.dimensinfin.eveonline.neocom.domain.NeoComNode;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdPlanetsPlanetIdOkContents;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetUniverseTypesTypeIdOkDogmaAttributes;
import org.dimensinfin.eveonline.neocom.planetary.FacilityGeoPosition;
import org.dimensinfin.eveonline.neocom.planetary.PlanetType;
import org.dimensinfin.eveonline.neocom.planetary.PlanetaryFacilityType;
import org.dimensinfin.eveonline.neocom.planetary.domain.PlanetaryFacility;

public abstract class PlanetaryFacilityWrapper extends NeoComNode /*implements IPlanetaryFacility, IItemFacet*/ {
	protected PlanetaryFacility planetaryFacility;

	// - I P L A N E T A R Y F A C I L I T Y
//	@Override
	public String getName() {
		return this.planetaryFacility.getName();
	}

//	@Override
	public int getGroupId() {
		return this.planetaryFacility.getGroupId();
	}

//	@Override
	public PlanetaryFacilityType getFacilityType() {
		return this.planetaryFacility.getFacilityType();
	}

	//	@Override
	public Float getStorageCapacity() {
		return this.planetaryFacility.getStorageCapacity();
	}

//	@Override
//	public int getBackgroundColor() {
//		return planetaryFacility.getBackgroundColor();
//	}

//	@Override
	public FacilityGeoPosition getGeoPosition() {
		return this.planetaryFacility.getGeoPosition();
	}

//	@Override
	public Integer getSchematicId() {
		return this.planetaryFacility.getSchematicId();
	}

//	@Override
	public DateTime getLastCycleStart() {
		return this.planetaryFacility.getLastCycleStart();
	}

//	@Override
	public List<GetCharactersCharacterIdPlanetsPlanetIdOkContents> getContents() {
		return this.planetaryFacility.getContents();
	}

//	@Override
	public PlanetType getPlanetType() {
		return this.planetaryFacility.getPlanetType();
	}

//	@Override
	public int getCpuUsage() {
		return this.planetaryFacility.getCpuUsage();
	}

//	@Override
	public int getPowerUsage() {
		return this.planetaryFacility.getPowerUsage();
	}

//	@Override
	public FacilityGeoPosition getCommandCenterPosition() {
		return this.planetaryFacility.getCommandCenterPosition();
	}

//	@Override
	public void setCommandCenterPosition( final FacilityGeoPosition commandCenterPosition ) {
		this.planetaryFacility.setCommandCenterPosition(commandCenterPosition);
	}

//	@Override
	public GetUniverseTypesTypeIdOkDogmaAttributes getDogmaAttributeById( final int attributeId ) {
		return this.planetaryFacility.getDogmaAttributeById(attributeId);
	}

	// -  I I T E M F A C E T
//	@Override
	public String getURLForItem() {
		return this.planetaryFacility.getTypeIconURL();
	}
}
