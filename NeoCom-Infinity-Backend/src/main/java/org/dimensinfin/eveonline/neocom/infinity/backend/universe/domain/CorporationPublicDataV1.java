package org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain;

import java.util.Objects;

import org.dimensinfin.eveonline.neocom.domain.NeoComNode;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetAlliancesAllianceIdOk;
import org.dimensinfin.eveonline.neocom.esiswagger.model.GetCorporationsCorporationIdOk;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class CorporationPublicDataV1 extends NeoComNode {
	private static final long serialVersionUID = 981323756290749600L;
	private Integer corporationId;
	private transient GetCorporationsCorporationIdOk corporationPublicData;
	private PilotPublicDataV1 ceoPilotData;
	private transient GetAlliancesAllianceIdOk alliance;

	// - C O N S T R U C T O R S
	private CorporationPublicDataV1() {}

	// - G E T T E R S   &   S E T T E R S
	public GetAlliancesAllianceIdOk getAlliance() {
		return this.alliance;
	}

	public PilotPublicDataV1 getCeoPilotData() {
		return this.ceoPilotData;
	}

	public Integer getCorporationId() {
		return this.corporationId;
	}

	public GetCorporationsCorporationIdOk getCorporationPublicData() {
		return this.corporationPublicData;
	}

	// - V I R T U A L S
	public String getUrl4Icon() {
		return "http://image.eveonline.com/Corporation/" + this.corporationId + "_64.png";
	}

	// - B U I L D E R
	public static class Builder {
		private final CorporationPublicDataV1 onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new CorporationPublicDataV1();
		}

		public CorporationPublicDataV1 build() {
			return this.onConstruction;
		}

		public CorporationPublicDataV1.Builder withCeoPilotData( final PilotPublicDataV1 ceoPilotData ) {
			this.onConstruction.ceoPilotData = Objects.requireNonNull( ceoPilotData );
			return this;
		}

		public CorporationPublicDataV1.Builder withCorporationId( final Integer corporationId ) {
			this.onConstruction.corporationId = Objects.requireNonNull( corporationId );
			return this;
		}

		public CorporationPublicDataV1.Builder withCorporationPublicData( final GetCorporationsCorporationIdOk corporationPublicData ) {
			this.onConstruction.corporationPublicData = Objects.requireNonNull( corporationPublicData );
			return this;
		}
	}
}