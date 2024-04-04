export class ESIMiningOperation {
    public date: string
    public quantity: number
    public solar_system_id: number
    public type_id: number

    constructor(fields: object = {}) {
        Object.assign(this, fields)
        console.log('initialization->' + fields)
    }
}
