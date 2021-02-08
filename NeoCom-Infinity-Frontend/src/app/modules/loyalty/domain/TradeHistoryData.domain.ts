export class TradeHistoryData{
    public dateString: string
    public value: number

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
}
