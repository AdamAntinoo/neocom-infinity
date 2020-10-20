package org.dimensinfin.eveonline.neocom.infinity.support.corporation.rest.v1;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"jsonClass",
		"UUID",
		"assetId",
		"type",
		"locationId",
		"name",
		"groupId",
		"groupName",
		"categoryId",
		"categoryName",
		"tech",
		"volume",
		"price",
		"quantity",
		"parentContainerId"
})
public class NeoAssetJson {

	@JsonProperty("jsonClass")
	private String jsonClass;
	@JsonProperty("UUID")
	private Object uUID;
	@JsonProperty("assetId")
	private Long assetId;
	@JsonProperty("type")
	private Integer type;
	@JsonProperty("locationId")
	private Long locationId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("groupId")
	private Integer groupId;
	@JsonProperty("groupName")
	private String groupName;
	@JsonProperty("categoryId")
	private Integer categoryId;
	@JsonProperty("categoryName")
	private String categoryName;
	@JsonProperty("tech")
	private String tech;
	@JsonProperty("volume")
	private Double volume;
	@JsonProperty("price")
	private Double price;
	@JsonProperty("quantity")
	private Integer quantity;
	@JsonProperty("parentContainerId")
	private Long parentContainerId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("jsonClass")
	public String getJsonClass() {
		return jsonClass;
	}

	@JsonProperty("jsonClass")
	public void setJsonClass(String jsonClass) {
		this.jsonClass = jsonClass;
	}

	@JsonProperty("UUID")
	public Object getUUID() {
		return uUID;
	}

	@JsonProperty("UUID")
	public void setUUID(Object uUID) {
		this.uUID = uUID;
	}

	@JsonProperty("assetId")
	public Long getAssetId() {
		return assetId;
	}

	@JsonProperty("assetId")
	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	@JsonProperty("type")
	public Integer getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(Integer type) {
		this.type = type;
	}

	@JsonProperty("locationId")
	public Long getLocationId() {
		return locationId;
	}

	@JsonProperty("locationId")
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("groupId")
	public Integer getGroupId() {
		return groupId;
	}

	@JsonProperty("groupId")
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@JsonProperty("groupName")
	public String getGroupName() {
		return groupName;
	}

	@JsonProperty("groupName")
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@JsonProperty("categoryId")
	public Integer getCategoryId() {
		return categoryId;
	}

	@JsonProperty("categoryId")
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@JsonProperty("categoryName")
	public String getCategoryName() {
		return categoryName;
	}

	@JsonProperty("categoryName")
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@JsonProperty("tech")
	public String getTech() {
		return tech;
	}

	@JsonProperty("tech")
	public void setTech(String tech) {
		this.tech = tech;
	}

	@JsonProperty("volume")
	public Double getVolume() {
		return volume;
	}

	@JsonProperty("volume")
	public void setVolume(Double volume) {
		this.volume = volume;
	}

	@JsonProperty("price")
	public Double getPrice() {
		return price;
	}

	@JsonProperty("price")
	public void setPrice(Double price) {
		this.price = price;
	}

	@JsonProperty("quantity")
	public Integer getQuantity() {
		return quantity;
	}

	@JsonProperty("quantity")
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@JsonProperty("parentContainerId")
	public Long getParentContainerId() {
		return parentContainerId;
	}

	@JsonProperty("parentContainerId")
	public void setParentContainerId(Long parentContainerId) {
		this.parentContainerId = parentContainerId;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}