package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain;

import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import org.dimensinfin.eveonline.neocom.esiswagger.model.CharacterscharacterIdfittingsItems;

public class FittingItemModel extends RepresentationModel<FittingItemModel> {
	private Integer typeId;
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

	public FittingItemModel setTypeData( final Link typeData ) {
		this.typeData = typeData;
		return this;
	}

	public int getTypeId() {
		return this.typeId;
	}

	@JsonProperty("_links")
	public void setLinks( final Map<String, Link> links ) {
		links.forEach( ( label, link ) -> this.add( link.withRel( label ) ) );
	}

	// - C O R E
	@Override
	@NotNull
	public String toString() {
		return new ToStringBuilder( this, ToStringStyle.JSON_STYLE )
				.append( "typeId", this.typeId )
				.append( "flag", this.flag )
				.append( "quantity", this.quantity )
				.append( "typeData", this.typeData )
				.toString();
	}

	@Override
	public boolean equals( final Object o ) {
		if (this == o) return true;

		if (!(o instanceof FittingItemModel)) return false;

		final FittingItemModel that = (FittingItemModel) o;

		return new EqualsBuilder()
				.appendSuper( super.equals( o ) )
				.append( this.typeId, that.typeId )
				.append( this.flag, that.flag )
				.append( this.quantity, that.quantity )
				.append( this.typeData, that.typeData )
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder( 17, 37 )
				.appendSuper( super.hashCode() )
				.append( this.typeId )
				.append( this.flag )
				.append( this.quantity )
				.append( this.typeData )
				.toHashCode();
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

		public FittingItemModel.Builder withTypeId( final Integer typeId ) {
			this.onConstruction.typeId = Objects.requireNonNull( typeId );
			return this;
		}

		public FittingItemModel.Builder withTypeLink( final Link type ) {
			this.onConstruction.typeData = Objects.requireNonNull( type );
			return this;
		}
	}
}
