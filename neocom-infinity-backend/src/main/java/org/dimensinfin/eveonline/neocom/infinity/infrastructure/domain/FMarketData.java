package org.dimensinfin.eveonline.neocom.infinity.infrastructure.domain;

import lombok.Data;

@Data
public class FMarketData {
	private double weightedAverage;
	private double max;
	private double min;
	private double stddev;
	private double median;
	private long volume;
	private int orderCount;
	private double percentile;
}
