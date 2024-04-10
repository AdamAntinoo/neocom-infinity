export class ESIMiningOperation {
    public id : string
    public date: string
    public quantity: number
    public solarSystem: string
    public typeId: string

    constructor(fields: object = {}) {
        Object.assign(this, fields)
        console.log('ESIMiningOperation initialization->' + fields)
    }
}
