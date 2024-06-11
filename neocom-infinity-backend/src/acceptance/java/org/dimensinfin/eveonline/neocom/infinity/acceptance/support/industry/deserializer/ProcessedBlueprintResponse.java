package org.dimensinfin.eveonline.neocom.infinity.acceptance.support.industry.deserializer;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.dimensinfin.eveonline.neocom.domain.EsiType;
import org.dimensinfin.eveonline.neocom.domain.space.SpaceLocation;
import org.dimensinfin.eveonline.neocom.industry.domain.Resource;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
@Getter
@Setter
public class ProcessedBlueprintResponse {
	@SerializedName("uid")
	@Expose
	private String uid;
	@SerializedName("typeId")
	@Expose
	private Integer typeId;
	@SerializedName("blueprint")
	@Expose
	private EsiType blueprint;
	@SerializedName("output")
	@Expose
	private EsiType output;
	@SerializedName("location")
	@Expose
	private SpaceLocation location;
	@SerializedName("billOfMaterials")
	@Expose
	private List<Resource> billOfMaterials;
	@SerializedName("materialEfficiency")
	@Expose
	private Integer materialEfficiency;
	@SerializedName("timeEfficiency")
	@Expose
	private Integer timeEfficiency;
	@SerializedName("manufactureCost")
	@Expose
	private Double manufactureCost;
	@SerializedName("outputCost")
	@Expose
	private Double outputCost;
	@SerializedName("index")
	@Expose
	private Double index;
}
