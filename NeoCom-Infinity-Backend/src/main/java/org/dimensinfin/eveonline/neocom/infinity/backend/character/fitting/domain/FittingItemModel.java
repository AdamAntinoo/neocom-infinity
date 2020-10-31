package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain;

import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import org.dimensinfin.eveonline.neocom.esiswagger.model.CharacterscharacterIdfittingsItems;

public class FittingItemModel extends RepresentationModel<FittingItemModel> {
	private CharacterscharacterIdfittingsItems.FlagEnum flag;
	private Integer quantity = 0;
	private Link typeData;

	// - C O N S T R U C T O R S
	private FittingItemModel() {}

	// - G E T T E R S   &   S E T T E R S
	public CharacterscharacterIdfittingsItems.FlagEnum getFlag() {
		return this.flag;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public Link getTypeData() {
		return this.typeData;
	}

	@JsonProperty("_links")
	public void setLinks( final Map<String, Link> links ) {
		links.forEach( ( label, link ) -> this.add( link.withRel( label ) ) );
	}

	public FittingItemModel setTypeData( final Link typeData ) {
		this.typeData = typeData;
		return this;
	}

	// - B U I L D E R
	public static class Builder {
		private final FittingItemModel onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new FittingItemModel();
		}

		public FittingItemModel build() {
			return this.onConstruction;
		}

		public FittingItemModel.Builder withQuantity( final Integer quantity ) {
			this.onConstruction.quantity = Objects.requireNonNull( quantity );
			return this;
		}

		public FittingItemModel.Builder withSlotFlag( final CharacterscharacterIdfittingsItems.FlagEnum flag ) {
			this.onConstruction.flag = Objects.requireNonNull( flag );
			return this;
		}

		public FittingItemModel.Builder withTypeLink( final Link type ) {
			this.onConstruction.typeData = Objects.requireNonNull( type );
			return this;
		}
	}
}
