package org.dimensinfin.eveonline.neocom.infinity.support.neoitem.rest.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class NeoItemTransport {
	private String jsonClass;
	private Integer typeId;
	private String name;
	private Integer groupId;
	private String groupName;
	private Integer categoryId;
	private String categoryName;
	private String tech;
	private Double volume;
	private Double price;
	private Boolean isBlueprint = false;
	private String urlforItem;

	private NeoItemTransport() {}

	public String getJsonClass() {
		return jsonClass;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public String getName() {
		return name;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getTech() {
		return tech;
	}

	public Double getVolume() {
		return volume;
	}

	public Double getPrice() {
		return price;
	}

	public Boolean isBlueprint() {
		return isBlueprint;
	}

	public String getUrlforItem() {
		return urlforItem;
	}
}