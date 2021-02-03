export class SessionStateResponse {
    public state: string
    constructor(values: Object = {}) {
        Object.assign(this, values);
    }
}
