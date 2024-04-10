package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.RepresentationModel;

import org.dimensinfin.eveonline.neocom.domain.EsiType;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@Deprecated
public class FittingModel extends RepresentationModel<FittingModel> {
	private final List<FittingItemModel> fittingItems = new ArrayList<>();
	private Integer fittingId;
	private String name;
	private String description;
	private EsiType shipHull;

	// - C O N S T R U C T O R S
	private FittingModel() {}

	// - G E T T E R S   &   S E T T E R S
	public String getDescription() {
		return this.description;
	}

	public Integer getFittingId() {
		return this.fittingId;
	}

	public List<FittingItemModel> getFittingItems() {
		return this.fittingItems;
	}

	public String getName() {
		return this.name;
	}

	public EsiType getShipHull() {
		return this.shipHull;
	}

	public int getShipTypeId() {
		return this.shipHull.getTypeId();
	}

	public void addFittingItem( final FittingItemModel fittingItem ) {
		this.fittingItems.add( fittingItem );
	}

	// - C O R E
	@Override
	@NotNull
	public String toString() {
		return new ToStringBuilder( this, ToStringStyle.JSON_STYLE )
				.append( "fittingId", this.fittingId )
				.append( "name", this.name )
				.append( "description", this.description )
				.append( "shipHull", this.shipHull )
				.toString();
	}

	@Override
	public boolean equals( final Object o ) {
		if (this == o) return true;

		if (!(o instanceof FittingModel)) return false;

		final FittingModel that = (FittingModel) o;

		return new EqualsBuilder()
				.appendSuper( super.equals( o ) )
				.append( this.fittingId, that.fittingId )
				.append( this.name, that.name )
				.append( this.description, that.description )
				.append( this.shipHull, that.shipHull )
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder( 17, 37 )
				.appendSuper( super.hashCode() )
				.append( this.fittingId )
				.append( this.name )
				.append( this.description )
				.append( this.shipHull )
				.toHashCode();
	}

	// - B U I L D E R
	public static class Builder {
		private final FittingModel onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new FittingModel();
		}

		public FittingModel build() {
			Objects.requireNonNull( this.onConstruction.fittingId );
			Objects.requireNonNull( this.onConstruction.shipHull );
			return this.onConstruction;
		}

		public FittingModel.Builder withDescription( final String description ) {
			this.onConstruction.description = Objects.requireNonNull( description );
			return this;
		}

		public FittingModel.Builder withFittingId( final Integer fittingId ) {
			this.onConstruction.fittingId = Objects.requireNonNull( fittingId );
			return this;
		}

		public FittingModel.Builder withName( final String name ) {
			this.onConstruction.name = Objects.requireNonNull( name );
			return this;
		}

		public FittingModel.Builder withShipHull( final EsiType shipHull ) {
			this.onConstruction.shipHull = Objects.requireNonNull( shipHull );
			return this;
		}
	}
}
