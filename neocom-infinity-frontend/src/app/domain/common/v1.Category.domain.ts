import { NeoCom } from "@domain/NeoCom.domain";

export class V1Category extends NeoCom{
    public jsonClass: string = 'BlueprintCategory'
    public categoryId: number
    public name: string

    public constructor(values: Object = {}) {
        super(values)
        // this.jsonClass = this.identify()
    }
    // public identify(): string {
    //     return 'BlueprintCategory'
    // }
}
