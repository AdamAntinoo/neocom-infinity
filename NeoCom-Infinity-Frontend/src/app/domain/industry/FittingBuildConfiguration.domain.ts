// - DOMAIN
import { BaseNode } from '@domain/core/BaseNode.domain'
import { ICollaboration } from '@domain/interfaces/ICollaboration.interface'
import { BuildActionDao } from './BuildActionDao.dao'
import { HullDao } from './HullDao.dao'
/**
 * This class contains all the Fitting data hierarchy as processed from the backend.
 * It uses the Node factory to collaborate contents to the automatic node render factory so content management is simple to render and control.
 */
export class FittingBuildConfiguration extends BaseNode {
    public name: string
    public description: string
    public hullGroup: string
    public hops: number
    private hull: HullDao
    private actions: BuildActionDao[]

    constructor(values: Object = {}) {
        super()
        Object.assign(this, values)
        this.jsonClass = 'FittingBuildConfiguration'
        this.transform()
    }
    /**
     * Transform json serialized data into DAO Typescript instances.
     */
    protected transform(): void {
        if (null != this.hull) this.hull = new HullDao(this.hull)
        if (null != this.actions) {
            const transformedActions: BuildActionDao[] = []
            this.actions.forEach(action => {
                transformedActions.push(new BuildActionDao(action))
            })
            this.actions = transformedActions
        }
    }

    // - I C O L L A B O R A T I O N
    public collaborate2View(): ICollaboration[] {
        return [this];
    }
    // - G E T T E R S   &   S E T T E R S
    public getHullURLIcon(): string {
        return this.hull.getHullURLIcon()
    }
    public getHullClass(): string {
        return this.hull.getHullClass()
    }
    public getFittingName(): string {
        return this.name
    }
    public getHullGroup(): string {
        return this.hullGroup
    }
    // - D E L E G A T E D
    public getHullTypeId(): number {
        return this.hull.getHullTypeId()
    }
    public getHullTech(): string {
        return this.hull.getTech()
    }
    public getHullPrice(): number {
        return this.hull.getPrice()
    }
    public getMarketStation(): string {
        return this.hull.getMarketStation()
    }
    public getHullHops(): number {
        return this.hull.getHullHops()
    }
    public getHullHopTime(): number {
        return this.hull.getHullHopTime()
    }
    public getFittingItem(group: string, index: number): BuildActionDao {
        return new BuildActionDao(this.actions[index])
    }
}
