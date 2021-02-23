package org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain;

import java.util.Objects;

import org.joda.time.DateTime;

import org.dimensinfin.eveonline.neocom.esiswagger.model.GetStatusOk;
import org.dimensinfin.eveonline.neocom.provider.ESIDataProvider;
import org.dimensinfin.eveonline.neocom.utility.TimeUnitAgo;

public class ServerStatus {
	private String server = ESIDataProvider.DEFAULT_ESI_SERVER;
	private String backendVersion = "UNDEFINED";
	private String SDEVersion = "UNDEFINED";
	private GetStatusOk status;

	// - G E T T E R S   &   S E T T E R S
	public String getBackendVersion() {
		return this.backendVersion;
	}

	public String getSDEVersion() {
		return this.SDEVersion;
	}

	public String getServer() {
		return this.server;
	}

	public String getStartAgo() {
		return TimeUnitAgo.millisToLongDHMS( DateTime.now().getMillis() - this.status.getStartTime().getMillis() ) + " ago";
	}

	public GetStatusOk getStatus() {
		return this.status;
	}

	public String getTimeToNextDowntime() {
		final DateTime nextDowntime = new DateTime(
				this.status.getStartTime().getYear(),
				this.status.getStartTime().getMonthOfYear(),
				this.status.getStartTime().getDayOfMonth(),
				11, 00 ).plusDays( 1 );
		return "Downtime in " + TimeUnitAgo.millisToLongDHMS( nextDowntime.getMillis() - DateTime.now().getMillis() );
	}

	// - B U I L D E R
	public static class Builder {
		private final ServerStatus onConstruction;

		// - C O N S T R U C T O R S
		public Builder() {
			this.onConstruction = new ServerStatus();
		}

		public ServerStatus build() {
			Objects.requireNonNull( this.onConstruction.server );
			Objects.requireNonNull( this.onConstruction.status );
			return this.onConstruction;
		}

		public ServerStatus.Builder withBackendVersion( final String version ) {
			this.onConstruction.backendVersion = Objects.requireNonNull( version );
			return this;
		}

		public ServerStatus.Builder withSDEVersion( final String version ) {
			this.onConstruction.SDEVersion = Objects.requireNonNull( version );
			return this;
		}

		public ServerStatus.Builder withServer( final String server ) {
			if (null != server) this.onConstruction.server = Objects.requireNonNull( server );
			return this;
		}

		public ServerStatus.Builder withStatus( final GetStatusOk status ) {
			this.onConstruction.status = Objects.requireNonNull( status );
			return this;
		}
	}
}