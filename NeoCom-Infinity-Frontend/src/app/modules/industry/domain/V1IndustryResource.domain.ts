// - DOMAIN
import { NeoCom } from "@domain/NeoCom.domain";
export class IndustryResource extends NeoCom{
    public name: string
    public quantity : number=1
    public price : number 
    constructor(values: Object = {}) {
        super()
        Object.assign(this, values);
        this.jsonClass = 'IndustryResource';
    }
}
