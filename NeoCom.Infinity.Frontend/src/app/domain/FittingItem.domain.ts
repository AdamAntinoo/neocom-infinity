// - DOMAIN
import { NeoCom } from './NeoCom.domain';

export class FittingItem extends NeoCom {
    private typeId: number;
    private name: string;
    private location: string;

    constructor(values: Object = {}) {
        super();
        Object.assign(this, values);
        this.jsonClass = 'Fitting';
    }
}
