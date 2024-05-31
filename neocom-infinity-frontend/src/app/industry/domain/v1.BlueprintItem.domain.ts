import { NeoCom } from '@domain/NeoCom.domain'
import { V2UniverseType } from '@domain/esi/V2.UniverseType.domain'
import { V1SpaceLocation } from '@domain/esi/v1.SpaceLocation.domain'
import { NeoComError, MANDATORY_FIELD_MISSING } from 'neocom-domain'

export class V1BlueprintItem extends NeoCom {
	public override jsonClass: string = 'BlueprintDto'
	public id: number
	public type: V2UniverseType
	public storageLocation: V1SpaceLocation
	public storageLocationType: string
	public materialEfficiency: number = 0
	public timeEfficiency: number = 0
	public identicalQuantity: number = 1
	public runs: number = 1
	public original: boolean = false

	constructor(values: Object = {}) {
		super()
		Object.assign(this, values)
		this.jsonClass = 'BlueprintItem'
	}

	public getUniqueId(): string {
		return this.id.toString()
	}
	public getTypeIconUrl(): string {
		return this.type.getTypeIconUrl()
	}
	public getBlueprintName(): string {
		return this.type.name
	}

	public static Builder = class Builder {
		public inConstruction: V1BlueprintItem

		constructor(fields: Object = {}) {
			this.inConstruction = new V1BlueprintItem(fields)
		}
		public withType(type: V2UniverseType): Builder {
			if (undefined == type) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
			this.inConstruction.type = type
			return this
		}
		public withStorageLocation(storageLocation: V1SpaceLocation, type: string): Builder {
			if (undefined == storageLocation) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
			this.inConstruction.storageLocation = storageLocation
			this.inConstruction.storageLocationType = type
			return this
		}
		public build(): V1BlueprintItem {
			if (undefined == this.inConstruction.id) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
			if (undefined == this.inConstruction.type) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
			if (undefined == this.inConstruction.storageLocation) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
			if (undefined == this.inConstruction.storageLocationType) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()

			return this.inConstruction
		}
	}
}
