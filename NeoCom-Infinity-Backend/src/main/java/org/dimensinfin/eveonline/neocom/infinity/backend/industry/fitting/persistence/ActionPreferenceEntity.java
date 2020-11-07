package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.persistence;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.domain.ActionType;

@Entity
@Table(name = "ActionPreferences", schema = "neocom")
public class ActionPreferenceEntity {
	public static String uniqueIdConstructor( final @NotNull String prefix, final @NotNull Integer pilotId, final @NotNull Integer fittingId ) {
		return prefix + "." + pilotId + "-" + fittingId;
	}

	@Id
	@NotNull(message = "ActionPreference unique 'id' is a mandatory field and cannot be null.")
	@Size(max = 32)
	@Column(name = "id", updatable = false, nullable = false)
	private String id;
	@NotNull(message = "FittingId unique 'id' is a mandatory field and cannot be null.")
	@Column(name = "fittingId", nullable = false)
	private Integer fittingId;
	@NotNull(message = "TypeId unique 'id' is a mandatory field and cannot be null.")
	@Column(name = "typeId",  nullable = false)
	private Integer typeId;
	@Column(name = "action",  nullable = false)
	@Enumerated(EnumType.STRING)
	private ActionType action = ActionType.BUY;
	@Column(name = "action",  nullable = false)
	private boolean saved = false; // A true on this field sets this rule as a saved rule to be applied to initial setup.

	// - C O N S T R U C T O R S
	private ActionPreferenceEntity() {}

	// - G E T T E R S   &   S E T T E R S
	public String getId() {
		return this.id;
	}

	public Integer getFittingId() {
		return this.fittingId;
	}

	// - B U I L D E R
	public static class Builder {
		private final ActionPreferenceEntity onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new ActionPreferenceEntity();
		}

		public ActionPreferenceEntity build() {
			Objects.requireNonNull( this.onConstruction.id );
			return this.onConstruction;
		}

		public ActionPreferenceEntity.Builder withUniqueId( final @NotNull String uniqueId ) {
			this.onConstruction.id = Objects.requireNonNull( uniqueId );
			return this;
		}
		public ActionPreferenceEntity.Builder withFittingId( final @NotNull Integer fittingId ) {
			this.onConstruction.fittingId = Objects.requireNonNull( fittingId );
			return this;
		}
		public ActionPreferenceEntity.Builder withTypeId( final @NotNull Integer typeId ) {
			this.onConstruction.typeId = Objects.requireNonNull( typeId );
			return this;
		}
	}
}
