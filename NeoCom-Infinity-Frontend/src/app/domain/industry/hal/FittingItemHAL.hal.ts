import { HALNode } from '@domain/hal/HALNode.hal';

export class FittingItemHAL extends HALNode {
    public location: string

    constructor(values: Object = {}) {
        super()
        Object.assign(this, values)
        // this.jsonClass = 'FittingBuildContentDao'
        // this.transform()
    }
}
