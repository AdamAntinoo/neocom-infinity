export class TradeHistoryData{
    public name: string
    public value: number

    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
}
