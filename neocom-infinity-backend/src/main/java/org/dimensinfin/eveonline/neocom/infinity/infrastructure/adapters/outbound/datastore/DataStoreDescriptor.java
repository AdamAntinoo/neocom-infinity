package org.dimensinfin.eveonline.neocom.infinity.infrastructure.adapters.outbound.datastore;

import java.util.function.Function;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;

import io.micrometer.core.instrument.Counter;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "with")
public class DataStoreDescriptor implements IDescriptor {
	private String keyPrefix;
	private Integer TTL;
	private Function<Long, String> uniqueKeyGenerator;
	private Counter totalCounter;
	private Counter hitsCounter;
	private Counter missCounter;

	public EsiType countHit( final EsiType type ) {
		this.totalCounter.increment();
		this.hitsCounter.increment();
		return type;
	}

	public EsiType countMiss( final EsiType type ) {
		this.totalCounter.increment();
		this.missCounter.increment();
		return type;
	}

	public SpaceLocation countHit( final SpaceLocation spaceLocation ) {
		this.totalCounter.increment();
		this.hitsCounter.increment();
		return spaceLocation;
	}

	public SpaceLocation countMiss( final SpaceLocation spaceLocation ) {
		this.totalCounter.increment();
		this.missCounter.increment();
		return spaceLocation;
	}

	@Override
	public String toString() {
		return new ToStringBuilder( this, ToStringStyle.JSON_STYLE )
				.append( "keyPrefix", keyPrefix )
				.append( "TTL", TTL )
				.append( "uniqueKeyGenerator", uniqueKeyGenerator )
				.append( "hitsCounter", hitsCounter )
				.toString();
	}
}
