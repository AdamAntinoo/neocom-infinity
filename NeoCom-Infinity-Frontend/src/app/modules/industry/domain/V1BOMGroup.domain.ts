// - DOMAIN
import { NeoCom } from "@domain/NeoCom.domain";
import { ICollaboration } from "@innovative/domain/interfaces/ICollaboration.interface";
import { AppCoreStoreService } from "@innovative/services/AppCoreStoreService.service";
import { IndustryResource } from "./V1IndustryResource.domain";

export class BOMGroup extends NeoCom {
    private label: string
    private contents: IndustryResource[] = []
    private totalCost: number = 0.0

    constructor(values: Object = {}) {
        super()
        Object.assign(this, values);
        this.jsonClass = 'BOMGroup';
    }

    public getLabel(): string {
        return this.label
    }
    public setLabel(label: string): BOMGroup {
        if (label) this.label = label
        return this
    }
    public getTotalCost () : number {
        return this.totalCost
    }
    public addResource(resource: IndustryResource): void {
        this.contents.push(resource)
    }
    public getResouces(): IndustryResource[] {
        return this.contents
    }

    // - I C O L L A B O R A T I O N
    public collaborate2View(appModelStore?: AppCoreStoreService, variant?: string): ICollaboration[] {
        const collaboration = []
        this.totalCost = 0.0
        collaboration.push(this)
        this.contents.forEach(element => {
            collaboration.push(element)
            this.totalCost += element.price * element.quantity
        });
        return collaboration
    }
}
export class BOMGroupBuilder {
    private onConstruction: BOMGroup

    constructor(values: Object = {}) {
        this.onConstruction = new BOMGroup(values)
        this.onConstruction.jsonClass = 'BOMGroup';
    }
    public withLabel(label: string): BOMGroupBuilder {
        this.onConstruction.setLabel(label)
        return this
    }
    public build(): BOMGroup {
        return this.onConstruction
    }
}
