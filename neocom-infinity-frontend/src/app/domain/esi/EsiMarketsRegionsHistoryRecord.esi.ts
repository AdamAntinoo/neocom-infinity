export class EsiMarketsRegionsHistoryRecord {
    public average:number
    public date:string
    public highest:number
    public lowest:number
    public order_count:number
    public volume:number

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
}
