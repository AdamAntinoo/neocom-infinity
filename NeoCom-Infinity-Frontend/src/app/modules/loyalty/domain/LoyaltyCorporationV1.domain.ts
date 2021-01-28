import { NeoCom } from "@domain/NeoCom.domain";

export class LoyaltyCorporationV1 extends NeoCom {
    public id: number
    public name: string

    constructor(values: Object = {}) {
        super()
        Object.assign(this, values)
        this.jsonClass = 'LoyaltyCorporationV1'
    }

    public getCorporationIconUrl(): string {
        return 'http://image.eveonline.com/Corporation/' + this.id + '_64.png'
    }
}
