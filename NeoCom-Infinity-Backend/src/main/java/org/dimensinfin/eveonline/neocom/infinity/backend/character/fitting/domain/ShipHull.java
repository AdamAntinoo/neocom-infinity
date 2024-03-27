package org.dimensinfin.eveonline.neocom.infinity.backend.character.fitting.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.dimensinfin.eveonline.neocom.infinity.backend.universe.domain.EsiItemModel;

@JsonIgnoreProperties
public class ShipHull /*extends EsiItemModel*/ {
	private Integer typeId;
	private String name;
	private Integer groupId;
	private String groupName;
	//			 "categoryId": 6,
	//			 "categoryName": "Ship",
	//			 "tech": "Tech I",
	//			 "volume": 29500.0,
	//			 "price": 226078.33,
	//			 "isBlueprint": false,
	//			 "urlforItem": "https://image.eveonline.com/Type/32880_64.png"

	// - C O N S T R U C T O R S
//	private ShipHull() {}

	// - G E T T E R S   &   S E T T E R S
//	public Integer getGroupId() {
//		return groupId;
//	}
//
//	public String getGroupName() {
//		return groupName;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public Integer getTypeId() {
//		return typeId;
//	}

	// - B U I L D E R
//	public static class Builder {
//		private final ShipHull onConstruction;
//
//		// - C O N S T R U C T O R S
//		public Builder() {
//			this.onConstruction = new ShipHull();
//		}
//
//		public ShipHull build() {
//			return this.onConstruction;
//		}
//	}
}
