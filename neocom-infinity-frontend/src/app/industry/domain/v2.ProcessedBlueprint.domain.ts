import { NeoComConstants } from '@app/platform/NeocomConstants.platform'
import { NeoCom } from '@domain/NeoCom.domain'
import { V1Stack } from '@domain/esi/V1.Stack.domain'
import { V2UniverseType } from '@domain/esi/V2.UniverseType.domain'
import { V1SpaceLocation } from '@domain/esi/v1.SpaceLocation.domain'
import { MANDATORY_FIELD_MISSING, NeoComError } from 'neocom-domain'

export class V2ProcessedBlueprint extends NeoCom {
    public jsonClass:string='ExtendedBlueprint'
    public uid: string
	public blueprint: V2UniverseType
	public output: V2UniverseType
	public bom: V1Stack[]
	public materialEfficiency: number
	public timeEfficiency: number
	public location: V1SpaceLocation
	public manufactureCost: number
	public outputCost: number
	public index: number

	public identify(): string {
		return 'ProcessedBlueprint'
	}

	// - G E T T E R S
	public getName(): string {
		if (this.blueprint) return this.blueprint.name
	}
	public getUniqueId(): string {
		if (this.blueprint) return this.uid
		else return '-'
	}
	public getTypeIconURL(): string {
		if (this.blueprint) return NeoComConstants.TYPE_ICON_URL_PREFIX+this.blueprint.typeId+NeoComConstants.TYPE_ICON_URL_SUFFIX
		else return '-'
	}
	public getModuleName(): string {
		if (this.output) return this.output.name
		else return '-'
	}

    public static Builder = class Builder {
		public onConstruction: V2ProcessedBlueprint

		constructor(fields: Object = {}) {
			this.onConstruction = new V2ProcessedBlueprint(fields)
		}
		public withBlueprintItem(blueorint: V2UniverseType): Builder {
			if (undefined == blueorint) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
			this.onConstruction.blueprint = blueorint
			return this
		}
		public withOutputItem(output: V2UniverseType): Builder {
			if (undefined == output) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
			this.onConstruction.output = output
			return this
		}
		public withLocation(location: V1SpaceLocation): Builder {
			if (undefined == location) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
			this.onConstruction.location = location
			return this
		}
		public withBom(bom: V1Stack[]): Builder {
			if (undefined == bom) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
			this.onConstruction.bom = bom
			return this
		}
		public build(): V2ProcessedBlueprint {
			// if (undefined == this.onConstruction.id) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
			// if (undefined == this.onConstruction.blueprint) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
			// if (undefined == this.onConstruction.storageLocation) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()
			// if (undefined == this.onConstruction.storageLocationType) throw new NeoComError.Builder(MANDATORY_FIELD_MISSING).build()

			return this.onConstruction
		}
	}
}
