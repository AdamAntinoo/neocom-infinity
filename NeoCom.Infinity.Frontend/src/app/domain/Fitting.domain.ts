// - DOMAIN
import { NeoCom } from './NeoCom.domain';
import { FittingItem } from './FittingItem.domain';
import { NeoItem } from './NeoItem.domain';

export class Fitting extends NeoCom {
    private fittingId: number;
    private name: string;
    private description: string;
    private hullGroup: string;
    private shipHullInfo: NeoItem = new NeoItem();
    private items: FittingItem[] = [];

    constructor(values: Object = {}) {
        super();
        Object.assign(this, values);
        this.jsonClass = 'Fitting';
        // Transform child instances to class objects.
        if (null != this.shipHullInfo) {
            this.shipHullInfo = new NeoItem(this.shipHullInfo);
        }
        if (null != this.items) {
            let fittingItems: FittingItem[] = [];
            for (let item of this.items)
                fittingItems.push(new FittingItem(item));
            this.items = fittingItems;
        }
    }

    // - GETTERS & SETTERS
    public getFittingId(): number {
        return this.fittingId;
    }
    public getShipTypeId(): number {
        if (null != this.shipHullInfo) return this.shipHullInfo.typeId;
        else return -1;
    }
    public getShipName(): string {
        if (null != this.shipHullInfo) return this.shipHullInfo.name;
        else return "-";
    }
    public getHullCategory(): string {
        return this.hullGroup;
    }
    public getShipGroup(): string {
        if (null != this.shipHullInfo) return this.shipHullInfo.getGroupName();
        else return "-SHIP-";
    }
    public getShipGroupId(): number {
        if (null != this.shipHullInfo) return this.shipHullInfo.getGroupId();
        else return 0;
    }
    public getHullGroup(): string {
        return this.hullGroup;
    }
}
