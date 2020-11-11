package org.dimensinfin.eveonline.neocom.infinity.backend.industry.fitting.persistence;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.dimensinfin.eveonline.neocom.infinity.datamanagement.industry.domain.ActionType;

@Entity
@Table(name = "ActionPreferences", schema = "neocom")
public class ActionPreferenceEntity {
	@Id
	@NotNull(message = "ActionPreference unique 'id' is a mandatory field and cannot be null.")
	@Size(max = 32)
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id = UUID.randomUUID();
	@NotNull(message = "FittingConfigurationId unique 'id' is a mandatory field and cannot be null.")
	@Column(name = "fittingConfigurationId", nullable = false)
	private String fittingConfigurationId;
	@NotNull(message = "FittingId unique 'id' is a mandatory field and cannot be null.")
	@Column(name = "fittingId", nullable = false)
	private Integer fittingId;
	@NotNull(message = "TypeId unique 'id' is a mandatory field and cannot be null.")
	@Column(name = "typeId", nullable = false)
	private Integer typeId;
	@Column(name = "action", nullable = false)
	@Enumerated(EnumType.STRING)
	private ActionType action = ActionType.BUY;
	@Column(name = "saved", nullable = false)
	private boolean saved = false; // A true on this field sets this rule as a saved rule to be applied to initial setup.

	// - C O N S T R U C T O R S
	protected ActionPreferenceEntity() {}

	// - G E T T E R S   &   S E T T E R S
	public ActionType getAction() {
		return this.action;
	}

	public String getFittingConfigurationId() {
		return this.fittingConfigurationId;
	}

	public Integer getFittingId() {
		return this.fittingId;
	}

	public UUID getId() {
		return this.id;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public boolean isSaved() {
		return this.saved;
	}

	public ActionPreferenceEntity setSaved( final boolean saved ) {
		this.saved = saved;
		return this;
	}

	// - B U I L D E R
	public static class Builder {
		private final ActionPreferenceEntity onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new ActionPreferenceEntity();
		}

		public ActionPreferenceEntity build() {
			Objects.requireNonNull( this.onConstruction.fittingConfigurationId );
			Objects.requireNonNull( this.onConstruction.fittingId );
			Objects.requireNonNull( this.onConstruction.typeId );
			return this.onConstruction;
		}

		public ActionPreferenceEntity.Builder withFittingConfigurationId( final @NotNull String fittingConfigurationId ) {
			this.onConstruction.fittingConfigurationId = Objects.requireNonNull( fittingConfigurationId );
			return this;
		}

		public ActionPreferenceEntity.Builder withFittingId( final @NotNull Integer fittingId ) {
			this.onConstruction.fittingId = Objects.requireNonNull( fittingId );
			return this;
		}

		public ActionPreferenceEntity.Builder withSetActionType( final ActionType actionType ) {
			this.onConstruction.action = actionType;
			return this;
		}

		public ActionPreferenceEntity.Builder withTypeId( final @NotNull Integer typeId ) {
			this.onConstruction.typeId = Objects.requireNonNull( typeId );
			return this;
		}
	}
}
