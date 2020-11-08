// - DOMAIN
import { BaseNode } from '@domain/core/BaseNode.domain'
import { BuildActionDao } from './BuildActionDao.dao'
import { HullDao } from './HullDao.dao'

export class FittingBuildConfiguration extends BaseNode {
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
    public getHullName () : string {
        return this.hull.item['name']
    }
}
