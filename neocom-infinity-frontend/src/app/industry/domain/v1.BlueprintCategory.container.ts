import { NeoCom } from '@domain/NeoCom.domain'
import { V1BlueprintItem } from './v1.BlueprintItem.domain'
import { ICollaboration } from '@innovative/domain/interfaces/ICollaboration.interface'

export class V1BlueprintCategoryContainer extends NeoCom {
	public jsonClass: string = 'BlueprintCategoryContainer'
	public categoryId: number
	public name: string
	private blueprints: V1BlueprintItem[]

	public constructor(values: Object = {}) {
		super(values)
	}
	public addBlueprint(blueprint: V1BlueprintItem): V1BlueprintCategoryContainer {
		this.blueprints.push(blueprint)
		return this
	}
	// Make this element expandable on UI click
	public isExpandable(): boolean {
		return true
	}
	public getContents(): V1BlueprintItem[] {
		return this.blueprints
	}
	public collaborate2View(): ICollaboration[] {
		return this.blueprints
	}
}
