// - DOMAIN
import { NeoCom } from "@domain/NeoCom.domain";
export class IndustryResource extends NeoCom {
    public typeId: number = 34
    public name: string
    public quantity: number = 1
    public price: number
    constructor(values: Object = {}) {
        super()
        Object.assign(this, values);
        this.jsonClass = 'IndustryResource';
    }
    public getTypeId(): number {
        return this.typeId
    }
    public getName(): string {
        return this.name
    }
}
