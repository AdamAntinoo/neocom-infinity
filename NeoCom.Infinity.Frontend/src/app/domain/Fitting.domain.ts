// - DOMAIN
import { NeoCom } from './NeoCom.domain';
import { FittingItem } from './FittingItem.domain';
import { fi } from 'date-fns/locale';

export class Fitting extends NeoCom {
    private fittingId: number;
    private name: string;
    private description: string;
    private hullClass: string;
    private hullGroup: string;
    private hullIcon: string;
    private items: FittingItem[] = [];

    constructor(values: Object = {}) {
        super();
        Object.assign(this, values);
        this.jsonClass = 'Fitting';
        // Transform child instances to class objects.
        if (null != this.items) {
            let fittingItems: FittingItem[] = [];
            for (let item of this.items)
                fittingItems.push(new FittingItem(item));
            this.items = fittingItems;
        }
    }
}
