// - DOMAIN
import { NeoCom } from './NeoCom.domain';
import { ESeparator } from './interfaces/EPack.enumerated';

export class Separator extends NeoCom {
    public variation: ESeparator = ESeparator.ORANGE;

    constructor(values: Object = {}) {
        super();
        this.jsonClass = 'Separator';
    }
    public setVariation(newstate: ESeparator): Separator {
        this.variation = newstate;
        return this;
    }
}
