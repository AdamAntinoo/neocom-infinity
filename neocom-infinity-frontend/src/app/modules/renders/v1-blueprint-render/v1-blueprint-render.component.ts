// - CORE
import { Component } from '@angular/core'
import { V1BlueprintItem } from '@app/industry/domain/v1.BlueprintItem.domain'
import { V2ProcessedBlueprint } from '@app/industry/domain/v2.ProcessedBlueprint.domain'
import { V2NodeContainerRenderComponent } from '@app/modules/renders/v2-node-container-render/v2-node-container-render.component'
import { NeoComConstants } from '@app/platform/NeocomConstants.platform'

@Component({
	selector: 'v1-blueprint',
	templateUrl: './v1-blueprint-render.component.html',
	styleUrls: ['./v1-blueprint-render.component.scss'],
})
export class V1BlueprintRenderComponent extends V2NodeContainerRenderComponent {
	public getNode(): V2ProcessedBlueprint {
		return this.node as V2ProcessedBlueprint
	}
	public getUniqueId(): string {
		if (this.node) return this.getNode().getUniqueId()
		return '-'
	}
	public getBlueprintIconUrl(): string {
		if (this.node) return NeoComConstants.TYPE_ICON_URL_PREFIX + this.getNode().blueprint.typeId + NeoComConstants.TYPE_ICON_URL_SUFFIX
		else return NeoComConstants.DEFAULT_ICON_PLACEHOLDER
	}
	public getBlueprintName(): string {
		if (this.node) return this.getNode().blueprint.name
		return '-'
	}
	public getOutputName(): string {
		if (this.node) return this.getNode().output.name
		return '-'
	}
	public getManufactureCost(): number {
		if (this.node) return this.getNode().manufactureCost
		return 0.0
	}
	public getOutputPrice(): number {
		if (this.node) return this.getNode().outputCost
		return 0.0
	}
	public getCostIndex(): number {
		if (this.node) return this.getNode().index * 100.0
		return 0.0
	}
	public getOutputIconUrl(): string {
		if (this.node) return NeoComConstants.TYPE_ICON_URL_PREFIX + this.getNode().output.typeId + NeoComConstants.TYPE_ICON_URL_SUFFIX
		else return NeoComConstants.DEFAULT_ICON_PLACEHOLDER
	}
	// - Attributes for V1BlueprintItem
	public getTypeIconUrl(): string {
		const node = this.getNode() as unknown as V1BlueprintItem
		if (node) return node.type.getTypeIconUrl()
		else return NeoComConstants.DEFAULT_ICON_PLACEHOLDER
	}
	public getIdenticalsQuantity(): number {
		const node = this.getNode() as unknown as V1BlueprintItem
		if (node) return node.identicalQuantity
		else return 0
	}
}
