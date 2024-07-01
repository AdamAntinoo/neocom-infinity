package org.dimensinfin.eveonline.neocom.infinity.infrastructure.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class V2MarketDataDto {
	@Setter
	public int typeId;
	@Setter
	public int locationId;
	public Double buyPrice;
	public Integer buyOrderCount;
	public long buyVolume;
	public Double sellPrice;
	public Integer sellOrderCount;
	public long sellVolume;
}
