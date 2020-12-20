// - DOMAIN
import { BuildActionDto } from "@domain/industry/dto/BuildActionDto.dto";
import { IndustryResource } from "./V1IndustryResource.domain";

/**
 * A BOMResource is an extension to the generic 'Resource' or 'IndustryResource' tht also contains the Build Action that the user has connected to the item type. For example is the uses decides that 'Epithal' hulls are ever built then there is an action on the pilot configuration repository saying that and any BOM element that points to an epithal will have an associated action of type BUILD.
 */
export class BOMResource extends IndustryResource {
    protected action: BuildActionDto

    constructor(values: Object = {}) {
        super(values)
        this.jsonClass = 'BOMResource';
    }

    public getAction(): BuildActionDto {
        return this.action
    }
    public setAction(action: BuildActionDto): BOMResource {
        this.action = action
        return this
    }
}
export class BOMResourceBuilder {
    private onConstruction: BOMResource

    constructor(values: Object = {}) {
        this.onConstruction = new BOMResource(values)
        this.onConstruction.jsonClass = 'BOMResource';
    }
    public withBuildAction(action: BuildActionDto): BOMResourceBuilder {
        if (action) this.onConstruction.setAction(action)
        return this
    }
    public build(): BOMResource {
        return this.onConstruction
    }
}
