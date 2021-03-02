package org.dimensinfin.eveonline.neocom.infinity.backend.accountset.planetary.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dimensinfin.eveonline.neocom.domain.IContainerAggregator;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCharactersCharacterIdPlanetsPlanetIdOkContents;
import org.dimensinfin.eveonline.neocom.planetary.domain.PlanetaryResource;

public class PlanetaryStorage implements IContainerAggregator<PlanetaryResource>, Serializable {
	private static final long serialVersionUID = 110028978014639183L;
	private final List<PlanetaryResource> contents = new ArrayList<>();

// - C O N S T R U C T O R S
	public PlanetaryStorage( final List<GetCharactersCharacterIdPlanetsPlanetIdOkContents> contents ) {
		for (final GetCharactersCharacterIdPlanetsPlanetIdOkContents resource : contents) {
			int quantity = 0;
			if (null != resource.getAmount()) quantity = resource.getAmount().intValue();
			this.addPack( (PlanetaryResource) new PlanetaryResource( resource.getTypeId() ).setQuantity( quantity ) );
		}
	}

// - G E T T E R S   &   S E T T E R S
	// -  I C O N T A I N E R A G G R E G A T O R
	@Override
	public int getContentSize() {
		return this.contents.size();
	}

	@Override
	public int addPack( final PlanetaryResource itemPack ) {
		this.contents.add( itemPack );
		return this.getContentSize();
	}

	public List<PlanetaryResource> getContents() {
		return this.contents;
	}

	public double getTotalValue() {
		double total = 0.0;
		for (final PlanetaryResource node : this.contents)
			// TODO - Planetary resources have lost the price data
			total += 1.0 * node.getQuantity();
		return total;
	}

	public Float getTotalVolume() {
		Double total = 0.0;
		for (final PlanetaryResource node : this.contents)
			total += node.getVolume() * node.getQuantity();
		return total.floatValue();
	}
}
