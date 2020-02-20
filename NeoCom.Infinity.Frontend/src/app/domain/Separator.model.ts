// - DOMAIN
import { NeoCom } from './NeoCom.domain';
import { ESeparator } from './interfaces/EPack.enumerated';

export class Separator extends NeoCom {
    private variation: ESeparator = ESeparator.WHITE;

    constructor(values: Object = {}) {
        super();
        Object.assign(this, values);
        this.jsonClass = 'Separator';
    }

    // - GETTERS & SETTERS
    public getVariation(): ESeparator {
        return this.variation;
    }
    public setVariation(newstate: ESeparator): Separator {
        this.variation = newstate;
        return this;
    }
}
