import { Component, OnInit } from '@angular/core'
import { V2ProcessedBlueprint } from '@app/industry/domain/v2.ProcessedBlueprint.domain'
import { V2NodeContainerRenderComponent } from '@app/modules/renders/v2-node-container-render/v2-node-container-render.component'

@Component({
	selector: 'v1-extended-blueprint-render',
	templateUrl: './v1-extended-blueprint-render.component.html',
	styleUrls: ['./v1-extended-blueprint-render.component.scss'],
})
export class V1ExtendedBlueprintRenderComponent extends V2NodeContainerRenderComponent {
	public getNode(): V2ProcessedBlueprint {
		return this.node as V2ProcessedBlueprint
	}
	public override getUniqueId(): string {
		return this.getNode().getUniqueId()
	}
	public getTypeIconUrl(): string {
		return '-'
	}
	public getBlueprintName(): string {
		if (this.getNode()) return this.getNode().blueprint.name
		else return '-'
	}
	public getOutputName(): string {
		if (this.getNode()) return this.getNode().output.name
		else return '-'
	}
	public getMarketPrice(): number {
		if (this.getNode()) return this.getNode().outputCost
		else return 0
	}
	public getManufactureCost(): number {
		if (this.getNode()) return this.getNode().manufactureCost
		else return 0
	}
    public getProfitIndex(): number {
		if (this.getNode()) return this.getNode().index
		else return 0
	}
}
