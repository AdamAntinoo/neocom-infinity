import { Component } from '@angular/core'
import { V1BlueprintCategoryContainer } from '@app/industry/domain/v1.BlueprintCategory.container'
import { V2NodeContainerRenderComponent } from '@app/modules/renders/v2-node-container-render/v2-node-container-render.component'

@Component({
	selector: 'v1-blueprint-category',
	templateUrl: './v1-blueprint-category.component.html',
	styleUrls: ['./v1-blueprint-category.component.scss'],
})
export class V1BlueprintCategoryRender extends V2NodeContainerRenderComponent {
    public getNode(): V1BlueprintCategoryContainer{
        return this.node as V1BlueprintCategoryContainer
    }
	public getUniqueId(): string {
		return String(this.getNode().categoryId)
	}
    public getName(): string {
        return this.getNode().name
    }
}
