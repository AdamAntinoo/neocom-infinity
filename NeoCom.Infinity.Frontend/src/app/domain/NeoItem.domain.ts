// - DOMAIN
import { NeoCom } from './NeoCom.domain';

export class NeoItem extends NeoCom {
    public typeId: number;
    public name: string;
    public groupId: number;
    public groupName: string;
    public categoryId: number;
    public categoryName: string;
    public tech: string;
    public volume: number;
    public price: number;
    public isBlueprint: boolean;
    public urlforItem: string;

    constructor(values: Object = {}) {
        super();
        Object.assign(this, values);
        this.jsonClass = 'Fitting';
    }
    public getGroupId(): number {
        return this.groupId;
    }
    public getGroupName(): string {
        return this.groupName;
    }
}
