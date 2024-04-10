package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.deserializer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class ProcessedBlueprintResponse {
	@SerializedName("uid")
	@Expose
	private String uid;
	@SerializedName("blueprintTypeId")
	@Expose
	private Integer blueprintTypeId;
	@SerializedName("blueprintTypeName")
	@Expose
	private String blueprintTypeName;
	@SerializedName("blueprintTypeIconURL")
	@Expose
	private String blueprintTypeIconURL;
	@SerializedName("outputTypeId")
	@Expose
	private Integer outputTypeId;
	@SerializedName("outputTypeName")
	@Expose
	private String outputTypeName;
	@SerializedName("outputTypeIconURL")
	@Expose
	private String outputTypeIconURL;
	@SerializedName("outputPrice")
	@Expose
	private Double outputPrice;
	@SerializedName("tradeStation")
	@Expose
	private TradeStation tradeStation;
	@SerializedName("manufactureMaterialCost")
	@Expose
	private Double manufactureMaterialCost;
	@SerializedName("costIndex")
	@Expose
	private Double costIndex;

// - G E T T E R S   &   S E T T E R S
	public String getBlueprintTypeIconURL() {
		return this.blueprintTypeIconURL;
	}

	public void setBlueprintTypeIconURL( final String blueprintTypeIconURL ) {
		this.blueprintTypeIconURL = blueprintTypeIconURL;
	}

	public Integer getBlueprintTypeId() {
		return this.blueprintTypeId;
	}

	public void setBlueprintTypeId( final Integer blueprintTypeId ) {
		this.blueprintTypeId = blueprintTypeId;
	}

	public String getBlueprintTypeName() {
		return this.blueprintTypeName;
	}

	public void setBlueprintTypeName( final String blueprintTypeName ) {
		this.blueprintTypeName = blueprintTypeName;
	}

	public Double getCostIndex() {
		return this.costIndex;
	}

	public void setCostIndex( final Double costIndex ) {
		this.costIndex = costIndex;
	}

	public Double getManufactureMaterialCost() {
		return this.manufactureMaterialCost;
	}

	public void setManufactureMaterialCost( final Double manufactureMaterialCost ) {
		this.manufactureMaterialCost = manufactureMaterialCost;
	}

	public Double getOutputPrice() {
		return this.outputPrice;
	}

	public void setOutputPrice( final Double outputPrice ) {
		this.outputPrice = outputPrice;
	}

	public String getOutputTypeIconURL() {
		return this.outputTypeIconURL;
	}

	public void setOutputTypeIconURL( final String outputTypeIconURL ) {
		this.outputTypeIconURL = outputTypeIconURL;
	}

	public Integer getOutputTypeId() {
		return this.outputTypeId;
	}

	public void setOutputTypeId( final Integer outputTypeId ) {
		this.outputTypeId = outputTypeId;
	}

	public String getOutputTypeName() {
		return this.outputTypeName;
	}

	public void setOutputTypeName( final String outputTypeName ) {
		this.outputTypeName = outputTypeName;
	}

	public TradeStation getTradeStation() {
		return this.tradeStation;
	}

	public void setTradeStation( final TradeStation tradeStation ) {
		this.tradeStation = tradeStation;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid( final String uid ) {
		this.uid = uid;
	}
}
